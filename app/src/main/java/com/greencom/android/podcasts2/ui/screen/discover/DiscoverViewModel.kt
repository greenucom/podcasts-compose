package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor,
) : MviViewModel<DiscoverViewState, DiscoverViewEvent, DiscoverViewSideEffect>() {

    override val initialViewState = DiscoverViewState.InitialLoading

    init {
        collectSelectableTrendingCategories()
        collectTrendingPodcastsForSelectedTrendingCategories()
    }

    override suspend fun handleEvent(event: DiscoverViewEvent) {
        TODO("Not yet implemented")
    }

    private fun collectSelectableTrendingCategories() = viewModelScope.launch {
        interactor.getSelectableTrendingCategories(Unit).collect { result ->
            result
                .onSuccess(::onCollectSelectableTrendingCategoriesSuccess)
                .onFailure(::onCollectSelectableTrendingCategoriesFailure)
        }
    }

    private fun collectTrendingPodcastsForSelectedTrendingCategories() = viewModelScope.launch {
        interactor.getTrendingPodcastsForSelectedTrendingCategories(Unit).collect { result ->
            result
                .onSuccess(::onCollectTrendingPodcastsForSelectedTrendingCategoriesSuccess)
                .onFailure(::onCollectTrendingPodcastsForSelectedTrendingCategoriesFailure)
        }
    }

    private fun onCollectSelectableTrendingCategoriesSuccess(
        selectableTrendingCategories: List<SelectableItem<Category>>,
    ) {
        val selectableTrendingCategoriesUiModels =
            mapSelectableTrendingCategoriesToUiModels(selectableTrendingCategories)
        updateStateWithSelectableTrendingCategories(selectableTrendingCategoriesUiModels)
    }

    private fun onCollectSelectableTrendingCategoriesFailure(t: Throwable) {
        TODO("Not yet implemented")
    }

    private fun onCollectTrendingPodcastsForSelectedTrendingCategoriesSuccess(podcasts: List<Podcast>) {
        val trendingPodcastsUiModels = podcasts.map { PodcastUiModel.fromPodcast(it) }
        TODO("Not yet implemented")
    }

    private fun onCollectTrendingPodcastsForSelectedTrendingCategoriesFailure(t: Throwable) {
        TODO("Not yet implemented")
    }

    private fun mapSelectableTrendingCategoriesToUiModels(
        selectableCategories: List<SelectableItem<Category>>,
    ): List<SelectableItem<CategoryUiModel>> {
        return selectableCategories.map {
            SelectableItem(
                item = CategoryUiModel.fromCategory(it.item),
                isSelected = it.isSelected,
            )
        }
    }

    private fun updateStateWithSelectableTrendingCategories(
        selectableTrendingCategories: List<SelectableItem<CategoryUiModel>>
    ) {
        updateState { current ->
            when (current) {
                DiscoverViewState.InitialLoading -> DiscoverViewState.Success(
                    selectableTrendingCategories = selectableTrendingCategories,
                )

                is DiscoverViewState.Success -> current.copy(
                    selectableTrendingCategories = selectableTrendingCategories,
                )
            }
        }
    }

}
