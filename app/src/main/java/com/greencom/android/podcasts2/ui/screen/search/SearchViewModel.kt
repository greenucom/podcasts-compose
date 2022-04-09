package com.greencom.android.podcasts2.ui.screen.search

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onQueryChange(query: String) {
        _query.update { query }
    }

    fun onImeSearch(query: String) {

    }

    fun onClearQuery() {
        _query.update { "" }
    }

    sealed interface ViewState {
        data class Success(val podcasts: List<Podcast>) : ViewState
        object Loading : ViewState
        object Empty : ViewState
        object Error : ViewState
    }

    sealed interface ViewEvent

}
