package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.mvi.State

@Immutable
data class SearchViewState(
    val query: String = QueryDefaultValue,
    val resultState: SearchResultState = SearchResultState.Empty,
    val showPodcastScreen: PodcastUiModel? = null,
) : State {

    companion object {
        private const val QueryDefaultValue = ""
    }

}

@Immutable
sealed interface SearchResultState {

    @Immutable
    object Empty : SearchResultState

    @Immutable
    object Loading : SearchResultState

    @Immutable
    data class Success(val podcasts: List<PodcastUiModel>) : SearchResultState

    @Immutable
    object Error : SearchResultState

}
