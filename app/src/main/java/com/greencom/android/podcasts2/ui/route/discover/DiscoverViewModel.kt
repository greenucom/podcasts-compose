package com.greencom.android.podcasts2.ui.route.discover

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.Event
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.common.mvi.SideEffect
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor,
) : MviViewModel<DiscoverViewModel.ViewState, DiscoverViewModel.ViewEvent, DiscoverViewModel.ViewSideEffect>() {

    override val initialViewState = ViewState.InitialLoading

    private val collectSelectableTrendingCategoriesJob = MutableStateFlow<Job?>(null)
    private val collectTrendingPodcastsForSelectedTrendingCategoriesJob =
        MutableStateFlow<Job?>(null)

    private var scrollNextTrendingPodcastListToTop = false

    init {
        collectSelectableTrendingCategories()
        collectTrendingPodcastsForSelectedTrendingCategories()
    }

    override suspend fun handleEvent(event: ViewEvent) = when (event) {
        is ViewEvent.ToggleSelectableTrendingCategory -> reduceToggleSelectableTrendingCategory(event.category)
        is ViewEvent.UpdateSubscriptionToPodcast -> reduceUpdateSubscriptionToPodcast(event.podcast)
        ViewEvent.RefreshTrendingPodcasts -> reduceRefreshTrendingPodcasts()
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

    private fun collectTrendingPodcastsForSelectedTrendingCategories() {
        collectTrendingPodcastsForSelectedTrendingCategoriesJob.getAndUpdate {
            viewModelScope.launch {
                interactor.getTrendingPodcastsForSelectedTrendingCategories(Unit).collect { result ->
                    result
                        .onSuccess(::updateStateWithTrendingPodcasts)
                        .onFailure(::updateStateWithTrendingPodcastsError)
                }
            }
        }
    }

    private fun reduceToggleSelectableTrendingCategory(category: CategoryUiModel) {
        val categoryDomain = category.toCategory()
        viewModelScope.launch {
            interactor.toggleSelectableTrendingCategory(categoryDomain)
        }
        scrollNextTrendingPodcastListToTop = true
    }

    private fun reduceUpdateSubscriptionToPodcast(podcast: PodcastUiModel) {
        viewModelScope.launch {
            interactor.updateSubscriptionToPodcast(podcast.toPodcast())
        }
    }

    private fun reduceRefreshTrendingPodcasts() {
        updateState {
            if (it is ViewState.Success) {
                it.copy(trendingPodcastsState = TrendingPodcastsState.Loading)
            } else {
                it
            }
        }
        scrollNextTrendingPodcastListToTop = true
        collectTrendingPodcastsForSelectedTrendingCategories()
    }

    private fun updateStateWithSelectableTrendingCategories(
        selectableTrendingCategories: List<SelectableItem<Category>>,
    ) {
        val categories = selectableTrendingCategories
            .map {
                SelectableItem(
                    item = CategoryUiModel.fromCategory(it.item),
                    isSelected = it.isSelected,
                )
            }
            .toImmutableList()

        updateState {
            ViewState.Success(
                selectableTrendingCategories = categories,
                trendingPodcastsState = TrendingPodcastsState.Loading,
            )
        }
    }

    private fun updateStateWithTrendingPodcasts(trendingPodcasts: List<Podcast>) {
        val podcasts = trendingPodcasts
            .map { PodcastUiModel.fromPodcast(it) }
            .toImmutableList()

        updateState {
            if (it is ViewState.Success) {
                it.copy(trendingPodcastsState = TrendingPodcastsState.Success(podcasts))
            } else {
                it
            }
        }

        if (scrollNextTrendingPodcastListToTop) {
            emitSideEffect(ViewSideEffect.ScrollToTop)
            scrollNextTrendingPodcastListToTop = false
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun updateStateWithTrendingPodcastsError(e: Throwable) {
        updateState {
            if (it is ViewState.Success) {
                it.copy(trendingPodcastsState = TrendingPodcastsState.Error)
            } else {
                it
            }
        }
    }

    @Stable
    sealed interface ViewState : State {
        object InitialLoading : ViewState

        @Immutable
        data class Success(
            val selectableTrendingCategories: ImmutableList<SelectableItem<CategoryUiModel>>,
            val trendingPodcastsState: TrendingPodcastsState,
        ) : ViewState
    }

    @Stable
    sealed interface ViewEvent : Event {
        data class ToggleSelectableTrendingCategory(val category: CategoryUiModel) : ViewEvent
        data class UpdateSubscriptionToPodcast(val podcast: PodcastUiModel) : ViewEvent
        object RefreshTrendingPodcasts : ViewEvent
    }

    @Stable
    sealed interface ViewSideEffect : SideEffect {
        object ScrollToTop : ViewSideEffect
    }

    @Stable
    sealed interface TrendingPodcastsState {
        object Loading : TrendingPodcastsState

        @Immutable
        data class Success(val trendingPodcasts: ImmutableList<PodcastUiModel>) : TrendingPodcastsState

        object Error : TrendingPodcastsState
    }

}
