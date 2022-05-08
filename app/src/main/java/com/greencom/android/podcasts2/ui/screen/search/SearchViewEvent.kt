package com.greencom.android.podcasts2.ui.screen.search

import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.mvi.Event

sealed interface SearchViewEvent : Event {
    object SearchPodcasts : SearchViewEvent
    data class QueryChanged(val query: String) : SearchViewEvent
    object ClearQuery : SearchViewEvent
    object RequestSearchFieldFocus : SearchViewEvent
    object ClearSearchFieldFocus : SearchViewEvent
    data class ChangeSubscription(val podcast: PodcastUiModel) : SearchViewEvent
    data class ShowPodcastScreen(val podcast: PodcastUiModel) : SearchViewEvent
    object PodcastScreenShown : SearchViewEvent
}
