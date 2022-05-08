package com.greencom.android.podcasts2.ui.screen.search

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.SearchPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMviViewModel @Inject constructor(
    private val interactor: SearchInteractor,
) : MviViewModel<SearchViewState, SearchViewEvent, SearchViewSideEffect>() {

    override val initialViewState = SearchViewState()

    override suspend fun handleEvent(event: SearchViewEvent) = when (event) {
        SearchViewEvent.SearchPodcasts -> reduceSearchPodcasts()
        is SearchViewEvent.QueryChanged -> reduceQueryChanged(event.query)
        SearchViewEvent.ClearQuery -> reduceClearQuery()
        SearchViewEvent.RequestSearchFieldFocus ->
            emitSideEffect(SearchViewSideEffect.RequestSearchFieldFocus)
        SearchViewEvent.ClearSearchFieldFocus ->
            emitSideEffect(SearchViewSideEffect.ClearSearchFieldFocus)
        is SearchViewEvent.ChangeSubscription -> reduceChangeSubscription(event.podcast)
        is SearchViewEvent.ShowPodcastScreen -> reduceShowPodcastScreen(event.podcast)
        SearchViewEvent.PodcastScreenShown -> reducePodcastScreenShown()
    }

    private val searchPodcastsJob = MutableStateFlow<Job?>(null)

    init {
        requestInitialSearchFieldFocus()
        collectSearchResults()
    }

    private fun requestInitialSearchFieldFocus() = viewModelScope.launch {
        delay(KeyboardInitialAppearingDelay)
        emitSideEffect(SearchViewSideEffect.RequestSearchFieldFocus)
    }

    private fun collectSearchResults() = viewModelScope.launch {
        interactor.getSearchPodcastsResultFlowUseCase(Unit).collect { podcasts ->
            val resultState = if (podcasts.isNotEmpty()) {
                SearchResultState.Success(podcasts)
            } else {
                SearchResultState.Empty
            }
            updateState { it.copy(resultState = resultState) }
        }
    }

    private fun searchPodcasts() {
        searchPodcastsJob.getAndUpdate { null }?.cancel()

        val query = state.value.query
        if (query.isBlank()) {
            updateState { it.copy(resultState = SearchResultState.Empty) }
            return
        }

        updateState { it.copy(resultState = SearchResultState.Loading) }
        searchPodcastsJob.getAndUpdate {
            viewModelScope.launch {
                val params = SearchPodcastsUseCase.Params(query.trim())
                interactor.searchPodcastsUseCase(params)
                    .onFailure(::onSearchPodcastsFailure)
            }
        }?.cancel()
    }

    private fun onSearchPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            updateState { it.copy(resultState = SearchResultState.Error) }
        }
    }

    private fun reduceSearchPodcasts() {
        searchPodcasts()
        emitSideEffect(SearchViewSideEffect.ClearSearchFieldFocus)
    }

    private fun reduceQueryChanged(query: String) {
        updateState { it.copy(query = query) }
    }

    private fun reduceClearQuery() {
        updateState { it.copy(query = QueryValueEmpty) }
    }

    private suspend fun reduceChangeSubscription(podcast: Podcast) {
        interactor.updatePodcastSubscriptionUseCase(podcast)
    }

    private suspend fun reduceShowPodcastScreen(podcast: Podcast) {
        interactor.savePodcastUseCase(podcast)
        updateState { it.copy(showPodcastScreen = podcast) }
    }

    private fun reducePodcastScreenShown() {
        updateState { it.copy(showPodcastScreen = null) }
    }

    companion object {
        private const val KeyboardInitialAppearingDelay = 75L
        private const val QueryValueEmpty = ""
    }

}
