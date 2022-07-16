package com.greencom.android.podcasts2.ui.route.search

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.SearchPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.mvi.Event
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.common.mvi.SideEffect
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.utils.cancelAndLaunchIn
import com.greencom.android.podcasts2.utils.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: SearchInteractor,
) : MviViewModel<SearchViewModel.ViewState, SearchViewModel.ViewEvent, SearchViewModel.ViewSideEffect>() {

    override val initialViewState = ViewState()

    private val searchPodcastsJob = MutableStateFlow<Job?>(null)

    override fun handleEvent(event: ViewEvent) = when (event) {
        is ViewEvent.TextFieldValueChanged -> reduceTextFieldValueChanged(event)
        ViewEvent.ClearTextField -> reduceClearTextField()
        ViewEvent.SearchPodcasts -> reduceSearchPodcasts()
        is ViewEvent.SearchCompleted -> reduceSearchCompleted(event)
        is ViewEvent.SearchFailed -> reduceSearchFailed(event)
        is ViewEvent.UpdateSubscriptionToPodcast -> reduceUpdateSubscriptionToPodcast(event)
    }

    private fun reduceTextFieldValueChanged(event: ViewEvent.TextFieldValueChanged) {
        updateState { it.copy(textFieldValue = event.textFieldValue) }
    }

    private fun reduceClearTextField() {
        updateState { it.copy(textFieldValue = emptyString()) }
    }

    private fun reduceSearchPodcasts() {
        updateState { it.copy(searchResultsState = SearchResultsState.Loading) }
        searchPodcasts()
    }

    private fun reduceSearchCompleted(event: ViewEvent.SearchCompleted) {
        val searchResultsState = if (event.podcasts.isNotEmpty()) {
            val podcasts = event.podcasts
                .map { PodcastUiModel.fromPodcast(it) }
                .toImmutableList()
            SearchResultsState.Success(podcasts)
        } else {
            SearchResultsState.NothingFound
        }

        updateState { it.copy(searchResultsState = searchResultsState) }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun reduceSearchFailed(event: ViewEvent.SearchFailed) {
        val searchResultsState = SearchResultsState.Error(R.string.something_went_wrong_check_connection)
        updateState { it.copy(searchResultsState = searchResultsState) }
    }

    private fun reduceUpdateSubscriptionToPodcast(event: ViewEvent.UpdateSubscriptionToPodcast) {
        viewModelScope.launch {
            interactor.updateSubscriptionToPodcast(event.podcast.toPodcast())
        }
    }

    private fun searchPodcasts() {
        searchPodcastsJob.cancelAndLaunchIn(viewModelScope) {
            val query = state.value.textFieldValue.trim()
            val params = SearchPodcastsUseCase.Params(query)
            interactor.searchPodcasts(params).collect { result ->
                result
                    .onSuccess {
                        val event = ViewEvent.SearchCompleted(it)
                        dispatchEvent(event)
                    }
                    .onFailure {
                        val event = ViewEvent.SearchFailed(it)
                        dispatchEvent(event)
                    }
            }
        }
    }

    @Immutable
    data class ViewState(
        val textFieldValue: String = emptyString(),
        val searchResultsState: SearchResultsState = SearchResultsState.QueryIsEmpty,
    ) : State

    @Stable
    sealed interface SearchResultsState {
        object QueryIsEmpty : SearchResultsState
        object Loading : SearchResultsState

        @Immutable
        data class Success(val podcasts: ImmutableList<PodcastUiModel>) : SearchResultsState

        object NothingFound : SearchResultsState

        @Immutable
        data class Error(@StringRes val message: Int) : SearchResultsState
    }

    @Stable
    sealed interface ViewEvent : Event {
        data class TextFieldValueChanged(val textFieldValue: String) : ViewEvent
        object ClearTextField : ViewEvent
        object SearchPodcasts : ViewEvent
        data class SearchCompleted(val podcasts: List<Podcast>) : ViewEvent
        data class SearchFailed(val exception: Throwable) : ViewEvent
        data class UpdateSubscriptionToPodcast(val podcast: PodcastUiModel) : ViewEvent
    }

    @Stable
    sealed interface ViewSideEffect : SideEffect

}
