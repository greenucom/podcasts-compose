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

    private val _query = MutableStateFlow(QueryDefaultValue)
    val query = _query.asStateFlow()

    private var searchPodcastsJob: Job? = null

    init {
        requestInitialFocusForSearchField()
        collectSearchResults()
    }

    private fun requestInitialFocusForSearchField() = viewModelScope.launch {
        delay(KeyboardAppearingDelay)
        sendViewEvent(ViewEvent.RequestFocusForSearchField)
    }

    private fun collectSearchResults() = viewModelScope.launch {
        interactor.getSearchPodcastsResultFlowUseCase(Unit).collect { podcasts ->
            updateViewState {
                if (podcasts.isNotEmpty()) ViewState.Success(podcasts) else ViewState.Empty
            }
        }
    }

    private fun searchPodcasts() {
        searchPodcastsJob?.cancel()

        val query = query.value
        if (query.isBlank()) {
            updateViewState { ViewState.Empty }
            return
        }

        updateViewState { ViewState.Loading }
        searchPodcastsJob = viewModelScope.launch {
            val params = SearchPodcastsUseCase.Params(query.trim())
            interactor.searchPodcastsUseCase(params)
                .onFailure(::onSearchPodcastsFailure)
        }
    }

    private fun onSearchPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            updateViewState { ViewState.Error }
        }
    }

    fun onQueryChanged(query: String) {
        _query.update { query }
    }

    fun onImeSearch() {
        searchPodcasts()
        sendViewEvent(ViewEvent.ClearFocusForSearchField)
    }

    fun onClearQuery() {
        _query.update { QueryDefaultValue }
    }

    fun onPodcastClicked(podcast: Podcast) = viewModelScope.launch {
        interactor.savePodcastUseCase(podcast)
        val event = ViewEvent.ShowPodcast(podcast)
        sendViewEvent(event)
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
        object RequestFocusForSearchField : ViewEvent
        object ClearFocusForSearchField : ViewEvent
        data class ShowPodcast(val podcast: Podcast) : ViewEvent
    }

    companion object {
        private const val QueryDefaultValue = ""
        private const val KeyboardAppearingDelay = 75L
    }

}
