package com.greencom.android.podcasts2.ui.screen.search

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.SearchPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
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

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private var searchPodcastsJob: Job? = null

    init {
        collectSearchResults()
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
            val params = SearchPodcastsUseCase.Params(query)
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
        searchPodcasts()
    }

    fun onImeSearch() {
        searchPodcasts()
    }

    fun onClearQuery() {
        _query.update { "" }
    }

    fun onSubscribedChanged(podcast: Podcast) = viewModelScope.launch {
        interactor.updatePodcastSubscriptionUseCase(podcast)
    }

    sealed interface ViewState {
        data class Success(val podcasts: List<Podcast>) : ViewState
        object Loading : ViewState
        object Empty : ViewState
        object Error : ViewState
    }

    sealed interface ViewEvent

}
