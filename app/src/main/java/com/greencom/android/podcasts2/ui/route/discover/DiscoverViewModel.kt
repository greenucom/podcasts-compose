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
import com.greencom.android.podcasts2.utils.cancelAndLaunchIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
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

    override fun handleEvent(event: ViewEvent) = when (event) {
        is ViewEvent.SelectableTrendingCategoriesReceived -> reduceSelectableTrendingCategoriesReceived(event)
        is ViewEvent.TrendingPodcastsReceived -> reduceTrendingPodcastsReceived(event)
        is ViewEvent.TrendingPodcastsReceivingFailed -> reduceTrendingPodcastsReceivingFailed(event)
        is ViewEvent.ToggleSelectableTrendingCategory -> reduceToggleSelectableTrendingCategory(event)
        is ViewEvent.UpdateSubscriptionToPodcast -> reduceUpdateSubscriptionToPodcast(event)
        ViewEvent.RefreshTrendingPodcasts -> reduceRefreshTrendingPodcasts()
        ViewEvent.SearchPodcastsClicked -> reduceSearchButtonClicked()
        ViewEvent.NavigationItemReselected -> reduceNavigationItemReselected()
    }

    private fun collectSelectableTrendingCategories() {
        collectSelectableTrendingCategoriesJob.cancelAndLaunchIn(viewModelScope) {
            interactor.getSelectableTrendingCategories(Unit).collect { result ->
                result.onSuccess {
                    val event = ViewEvent.SelectableTrendingCategoriesReceived(it)
                    dispatchEvent(event)
                }
            }
        }
    }

    private fun collectTrendingPodcastsForSelectedTrendingCategories() {
        collectTrendingPodcastsForSelectedTrendingCategoriesJob.cancelAndLaunchIn(viewModelScope) {
            interactor.getTrendingPodcastsForSelectedTrendingCategories(Unit).collect { result ->
                result
                    .onSuccess {
                        val event = ViewEvent.TrendingPodcastsReceived(it)
                        dispatchEvent(event)
                    }
                    .onFailure {
                        val event = ViewEvent.TrendingPodcastsReceivingFailed(it)
                        dispatchEvent(event)
                    }
            }
        }
    }

    private fun reduceSelectableTrendingCategoriesReceived(
        event: ViewEvent.SelectableTrendingCategoriesReceived,
    ) {
        val categories = event.selectableTrendingCategories
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

    private fun reduceTrendingPodcastsReceived(event: ViewEvent.TrendingPodcastsReceived) {
        val podcasts = event.trendingPodcasts
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
    private fun reduceTrendingPodcastsReceivingFailed(event: ViewEvent.TrendingPodcastsReceivingFailed) {
        updateState {
            if (it is ViewState.Success) {
                it.copy(trendingPodcastsState = TrendingPodcastsState.Error)
            } else {
                it
            }
        }
    }

    private fun reduceToggleSelectableTrendingCategory(
        event: ViewEvent.ToggleSelectableTrendingCategory,
    ) {
        viewModelScope.launch {
            val categoryDomain = event.category.toCategory()
            interactor.toggleSelectableTrendingCategory(categoryDomain)
            scrollNextTrendingPodcastListToTop = true
        }
    }

    private fun reduceUpdateSubscriptionToPodcast(event: ViewEvent.UpdateSubscriptionToPodcast) {
        viewModelScope.launch {
            interactor.updateSubscriptionToPodcast(event.podcast.toPodcast())
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

    private fun reduceSearchButtonClicked() {
        emitSideEffect(ViewSideEffect.NavigateToSearchRoute)
    }

    private fun reduceNavigationItemReselected() {
        emitSideEffect(ViewSideEffect.NavigationItemReselected)
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
        data class SelectableTrendingCategoriesReceived(
            val selectableTrendingCategories: List<SelectableItem<Category>>,
        ) : ViewEvent
        data class TrendingPodcastsReceived(val trendingPodcasts: List<Podcast>) : ViewEvent
        data class TrendingPodcastsReceivingFailed(val exception: Throwable) : ViewEvent
        data class ToggleSelectableTrendingCategory(val category: CategoryUiModel) : ViewEvent
        data class UpdateSubscriptionToPodcast(val podcast: PodcastUiModel) : ViewEvent
        object RefreshTrendingPodcasts : ViewEvent
        object SearchPodcastsClicked : ViewEvent
        object NavigationItemReselected : ViewEvent
    }

    @Stable
    sealed interface ViewSideEffect : SideEffect {
        object ScrollToTop : ViewSideEffect
        object NavigateToSearchRoute : ViewSideEffect
        object NavigationItemReselected : ViewSideEffect
    }

    @Stable
    sealed interface TrendingPodcastsState {
        object Loading : TrendingPodcastsState

        @Immutable
        data class Success(val trendingPodcasts: ImmutableList<PodcastUiModel>) : TrendingPodcastsState

        object Error : TrendingPodcastsState
    }

}
