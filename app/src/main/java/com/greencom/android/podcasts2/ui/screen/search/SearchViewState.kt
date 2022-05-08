package com.greencom.android.podcasts2.ui.screen.search

import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.mvi.State

data class SearchViewState(
    val query: String = QueryDefaultValue,
    val resultState: SearchResultState = SearchResultState.Empty,
    val showPodcastScreen: PodcastUiModel? = null,
) : State {

    companion object {
        private const val QueryDefaultValue = ""
    }

}

sealed interface SearchResultState {
    object Empty : SearchResultState
    object Loading : SearchResultState
    data class Success(val podcasts: List<PodcastUiModel>) : SearchResultState
    object Error : SearchResultState
}
