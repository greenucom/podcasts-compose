package com.greencom.android.podcasts2.ui.screen.search

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.SearchPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: SearchInteractor,
) : BaseViewModel<SearchViewModel.ViewState, SearchViewModel.ViewEvent>() {

    override val initialViewState = ViewState.Empty

    private val _query = MutableStateFlow(QUERY_DEFAULT_VALUE)
    val query = _query.asStateFlow()

    private var searchPodcastsJob: Job? = null
    private var onScrollJob: Job? = null

    init {
        requestInitialFocusForSearchField()
        collectSearchResults()
    }

    private fun requestInitialFocusForSearchField() = viewModelScope.launch {
        delay(KEYBOARD_APPEARING_DELAY)
        sendEventSuspend(ViewEvent.RequestInitialFocusForSearchField)
    }

    private fun collectSearchResults() = viewModelScope.launch {
        interactor.getSearchPodcastsResultFlowUseCase(Unit).collect { podcasts ->
            _viewState.update {
                if (podcasts.isNotEmpty()) ViewState.Success(podcasts) else ViewState.Empty
            }
        }
    }

    private fun searchPodcasts() {
        searchPodcastsJob?.cancel()

        val query = query.value
        if (query.isBlank()) {
            _viewState.update { ViewState.Empty }
            return
        }

        _viewState.update { ViewState.Loading }
        searchPodcastsJob = viewModelScope.launch {
            val params = SearchPodcastsUseCase.Params(query.trim())
            interactor.searchPodcastsUseCase(params)
                .onFailure(::onSearchPodcastsFailure)
        }
    }

    private fun onSearchPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            _viewState.update { ViewState.Error }
        }
    }

    fun onQueryChange(query: String) {
        _query.update { query }
    }

    fun onImeSearch() {
        searchPodcasts()
        sendEvent(ViewEvent.ClearFocusForSearchField)
    }

    fun onClearQuery() {
        _query.update { QUERY_DEFAULT_VALUE }
    }

    fun onScroll() {
        if (onScrollJob?.isCompleted != false) {
            onScrollJob = viewModelScope.launch {
                sendEventSuspend(ViewEvent.ClearFocusForSearchField)
                delay(ON_SCROLL_REPETITIONS_DELAY)
            }
        }
    }

    fun onSubscribedChanged(podcast: Podcast) = viewModelScope.launch {
        interactor.updatePodcastSubscriptionUseCase(podcast)
    }

    fun onTryAgainClicked() {
        searchPodcasts()
    }

    sealed interface ViewState {
        data class Success(val podcasts: List<Podcast>) : ViewState
        object Loading : ViewState
        object Empty : ViewState
        object Error : ViewState
    }

    sealed interface ViewEvent {
        object RequestInitialFocusForSearchField : ViewEvent
        object ClearFocusForSearchField : ViewEvent
    }

    companion object {
        private const val QUERY_DEFAULT_VALUE = ""
        private const val KEYBOARD_APPEARING_DELAY = 50L
        private const val ON_SCROLL_REPETITIONS_DELAY = 500L
    }

}
