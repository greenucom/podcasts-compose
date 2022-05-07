package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.RequestTrendingPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.Intent
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.common.mvi.SideEffect
import com.greencom.android.podcasts2.ui.common.mvi.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverMviViewModel @Inject constructor(
    private val interactor: DiscoverInteractor,
) : MviViewModel<
        DiscoverMviViewModel.ViewState,
        DiscoverMviViewModel.UserIntent,
        DiscoverMviViewModel.ViewSideEffect>() {

    override val initialViewState = ViewState()

    override suspend fun handleIntent(intent: UserIntent) = when (intent) {
        is UserIntent.ToggleSelectableCategory -> reduceToggleSelectableCategory(intent.category)
        is UserIntent.ClickPodcast -> reduceClickPodcast(intent.podcast)
        is UserIntent.ChangeSubscription -> reduceChangeSubscription(intent.podcast)
    }

    private var requestTrendingPodcastsJob = MutableStateFlow<Job?>(null)

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
            updateState { it.copy(trendingPodcastsState = TrendingPodcastsState.Error) }
        }
    }

    private fun collectTrendingPodcasts() = viewModelScope.launch {
        interactor.getTrendingPodcastsFlowUseCase(Unit).collect { podcasts ->
            val trendingPodcastsState = if (podcasts.isEmpty()) {
                TrendingPodcastsState.Loading
            } else {
                TrendingPodcastsState.Success(podcasts)
            }
            updateState { it.copy(trendingPodcastsState = trendingPodcastsState) }
        }
    }

    private suspend fun reduceToggleSelectableCategory(category: Category) {
        interactor.toggleSelectableTrendingCategoryUseCase(category)
    }

    private suspend fun reduceClickPodcast(podcast: Podcast) {
        interactor.savePodcastUseCase(podcast)
        // TODO: Navigation
    }

    private suspend fun reduceChangeSubscription(podcast: Podcast) {
        interactor.updatePodcastSubscriptionUseCase(podcast)
    }

    data class ViewState(
        val recommendedPodcastsState: RecommendedPodcastsState = RecommendedPodcastsState.Loading,
        val selectableCategories: List<SelectableItem<Category>> = emptyList(),
        val trendingPodcastsState: TrendingPodcastsState = TrendingPodcastsState.Loading,
    ) : State

    sealed interface RecommendedPodcastsState {
        object Loading : RecommendedPodcastsState
        data class Success(val podcasts: List<Podcast>) : RecommendedPodcastsState
    }

    sealed interface TrendingPodcastsState {
        object Loading : TrendingPodcastsState
        data class Success(val podcasts: List<Podcast>) : TrendingPodcastsState
        object Error : TrendingPodcastsState
    }

    sealed interface UserIntent : Intent {
        class ToggleSelectableCategory(val category: Category) : UserIntent
        class ClickPodcast(val podcast: Podcast) : UserIntent
        class ChangeSubscription(val podcast: Podcast) : UserIntent
    }

    sealed interface ViewSideEffect : SideEffect

    companion object {
        private const val TrendingPodcastCountMaxValue = 40
        private const val TrendingPodcastCountMinValue = 15
        private const val TrendingPodcastCountPerCategory = 5
    }

}
