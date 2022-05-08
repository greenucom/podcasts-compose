package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.mvi.Event

sealed interface DiscoverViewEvent : Event {
    data class ToggleSelectableCategory(val category: Category) : DiscoverViewEvent
    data class ShowPodcastScreen(val podcast: Podcast) : DiscoverViewEvent
    object PodcastScreenShown : DiscoverViewEvent
    data class ChangeSubscription(val podcast: Podcast) : DiscoverViewEvent
    object RefreshTrendingPodcasts : DiscoverViewEvent
    object ShowSearchScreen : DiscoverViewEvent
    object SearchScreenShown : DiscoverViewEvent
}
