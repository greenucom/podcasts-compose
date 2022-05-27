package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel
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

    private val collectTrendingPodcastsJob = MutableStateFlow<Job?>(null)

    init {
        collectSelectableTrendingCategories()
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

    private fun onCollectSelectableTrendingCategoriesSuccess(
        selectableTrendingCategories: List<SelectableItem<Category>>,
    ) {
        val selectableTrendingCategoriesUiModels =
            mapSelectableTrendingCategoriesToUiModels(selectableTrendingCategories)
        updateStateWithSelectableTrendingCategories(selectableTrendingCategoriesUiModels)

        val selectedTrendingCategories = selectableTrendingCategories
            .filter { it.isSelected }
            .map { it.item }
        collectTrendingPodcastsForSelectedTrendingCategories(selectedTrendingCategories)
    }

    private fun onCollectSelectableTrendingCategoriesFailure(t: Throwable) {
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

    private fun collectTrendingPodcastsForSelectedTrendingCategories(
        selectedTrendingCategories: List<Category>,
    ) {
        collectTrendingPodcastsJob.getAndUpdate {
            viewModelScope.launch {
                val params = createGetTrendingPodcastsParams(selectedTrendingCategories)
                interactor.getTrendingPodcasts(params).collect { result ->
                    result
                        .onSuccess(::onCollectTrendingPodcastsSuccess)
                        .onFailure(::onCollectTrendingPodcastsFailure)
                }
            }
        }?.cancel()
    }

    private fun createGetTrendingPodcastsParams(
        selectedTrendingCategories: List<Category>,
    ): GetTrendingPodcastsUseCase.Params {
        val max = if (selectedTrendingCategories.isNotEmpty()) {
            (selectedTrendingCategories.size * TrendingPodcastsCountPerCategory)
                .coerceIn(TrendingPodcastsMinSize, TrendingPodcastsMaxSize)
        } else {
            TrendingPodcastsMaxSize
        }

        return GetTrendingPodcastsUseCase.Params(
            max = max,
            inCategories = selectedTrendingCategories,
        )
    }

    private fun onCollectTrendingPodcastsSuccess(podcasts: List<Podcast>) {
        val podcastsUiModels = podcasts.map { PodcastUiModel.fromPodcast(it) }
        TODO("Not yet implemented")
    }

    private fun onCollectTrendingPodcastsFailure(t: Throwable) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TrendingPodcastsMinSize = 10
        private const val TrendingPodcastsMaxSize = 50
        private const val TrendingPodcastsCountPerCategory = 5
    }

}
