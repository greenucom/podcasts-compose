package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.RequestTrendingPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor,
) : MviViewModel<DiscoverViewState, DiscoverViewEvent, DiscoverViewSideEffect>() {

    override val initialViewState = DiscoverViewState()

    override suspend fun handleEvent(event: DiscoverViewEvent) = when (event) {
        is DiscoverViewEvent.ToggleSelectableCategory -> reduceToggleSelectableCategory(event.category)
        is DiscoverViewEvent.ShowPodcast -> reduceShowPodcast(event.podcast)
        is DiscoverViewEvent.ChangeSubscription -> reduceChangeSubscription(event.podcast)
        DiscoverViewEvent.RefreshTrendingPodcasts -> reduceRefreshTrendingPodcasts()
        DiscoverViewEvent.PodcastShown -> reducePodcastShown()
    }

    private val requestTrendingPodcastsJob = MutableStateFlow<Job?>(null)

    init {
        collectTrendingCategoriesAndRequestTrendingPodcasts()
        collectTrendingPodcasts()
    }

    private fun collectTrendingCategoriesAndRequestTrendingPodcasts() = viewModelScope.launch {
        val trendingCategories = interactor.getTrendingCategoriesUseCase(Unit)
        interactor.getSelectedTrendingCategoriesIdsUseCase(Unit).collect { ids ->
            val categories = trendingCategories.map { category ->
                SelectableItem(
                    item = category,
                    isSelected = category.id in ids,
                )
            }
            updateState { it.copy(selectableCategories = categories) }

            val selectedCategories = categories
                .filter { it.isSelected }
                .map { it.item }

            requestTrendingPodcastsForSelectedCategories(selectedCategories)
        }
    }

    private fun requestTrendingPodcastsForSelectedCategories(
        selectedCategories: List<Category>,
    ) {
        requestTrendingPodcastsJob.getAndUpdate {
            viewModelScope.launch {
                val max = if (selectedCategories.isEmpty()) {
                    TrendingPodcastCountMaxValue
                } else {
                    val value = selectedCategories.size * TrendingPodcastCountPerCategory
                    value.coerceIn(TrendingPodcastCountMinValue, TrendingPodcastCountMaxValue)
                }

                val params = RequestTrendingPodcastsUseCase.Params(
                    max = max,
                    inCategories = selectedCategories,
                )
                interactor.requestTrendingPodcastsUseCase(params)
                    .onFailure(::onRequestTrendingPodcastsFailure)
            }
        }?.cancel()
    }

    private fun onRequestTrendingPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            updateState { it.copy(trendingPodcastsState = DiscoverTrendingPodcastsState.Error) }
        }
    }

    private fun collectTrendingPodcasts() = viewModelScope.launch {
        interactor.getTrendingPodcastsFlowUseCase(Unit).collect { podcasts ->
            val trendingPodcastsState = if (podcasts.isEmpty()) {
                DiscoverTrendingPodcastsState.Loading
            } else {
                DiscoverTrendingPodcastsState.Success(podcasts)
            }
            updateState { it.copy(trendingPodcastsState = trendingPodcastsState) }
        }
    }

    private suspend fun reduceToggleSelectableCategory(category: Category) {
        interactor.toggleSelectableTrendingCategoryUseCase(category)
    }

    private suspend fun reduceShowPodcast(podcast: Podcast) {
        interactor.savePodcastUseCase(podcast)
        updateState { it.copy(showPodcast = podcast) }
    }

    private suspend fun reduceChangeSubscription(podcast: Podcast) {
        interactor.updatePodcastSubscriptionUseCase(podcast)
    }

    private fun reduceRefreshTrendingPodcasts() {
        updateState { it.copy(trendingPodcastsState = DiscoverTrendingPodcastsState.Loading) }

        val selectedCategories = state.value.selectableCategories
            .filter { it.isSelected }
            .map { it.item }
        requestTrendingPodcastsForSelectedCategories(selectedCategories)
    }

    private fun reducePodcastShown() {
        updateState { it.copy(showPodcast = null) }
    }

    companion object {
        private const val TrendingPodcastCountMaxValue = 40
        private const val TrendingPodcastCountMinValue = 15
        private const val TrendingPodcastCountPerCategory = 5
    }

}
