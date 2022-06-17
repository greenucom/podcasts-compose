package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor,
) : MviViewModel<DiscoverViewState, DiscoverViewEvent, DiscoverViewSideEffect>() {

    override val initialViewState = DiscoverViewState.InitialLoading

    private val collectSelectableTrendingCategoriesJob = MutableStateFlow<Job?>(null)

    init {
        collectSelectableTrendingCategories()
    }

    override suspend fun handleEvent(event: DiscoverViewEvent) = when (event) {
        is DiscoverViewEvent.ToggleSelectableTrendingCategory -> {
            reduceToggleSelectableTrendingCategory(event.category)
        }
    }

    private fun collectSelectableTrendingCategories() {
        collectSelectableTrendingCategoriesJob.getAndUpdate {
            viewModelScope.launch {
                interactor.getSelectableTrendingCategories(Unit).collect { result ->
                    result.onSuccess(::updateStateWithSelectableTrendingCategories)
                }
            }
        }?.cancel()
    }

    private fun reduceToggleSelectableTrendingCategory(category: CategoryUiModel) {
        val categoryDomain = category.toCategory()
        viewModelScope.launch {
            interactor.toggleSelectableTrendingCategory(categoryDomain)
        }
    }

    private fun updateStateWithSelectableTrendingCategories(
        selectableTrendingCategories: List<SelectableItem<Category>>,
    ) {
        val categories = selectableTrendingCategories.map {
            SelectableItem(
                item = CategoryUiModel.fromCategory(it.item),
                isSelected = it.isSelected,
            )
        }
        updateState {
            DiscoverViewState.Success(
                selectableTrendingCategories = categories,
            )
        }
    }

}
