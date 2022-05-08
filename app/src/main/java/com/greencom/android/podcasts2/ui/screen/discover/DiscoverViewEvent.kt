package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.mvi.Event

sealed interface DiscoverViewEvent : Event {
    class ToggleSelectableCategory(val category: Category) : DiscoverViewEvent
    class ShowPodcastScreen(val podcast: Podcast) : DiscoverViewEvent
    object PodcastScreenShown : DiscoverViewEvent
    class ChangeSubscription(val podcast: Podcast) : DiscoverViewEvent
    object RefreshTrendingPodcasts : DiscoverViewEvent
}
