package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.mvi.Event

sealed interface DiscoverViewEvent : Event {
    data class ToggleSelectableCategory(val category: CategoryUiModel) : DiscoverViewEvent
    data class ShowPodcastScreen(val podcast: PodcastUiModel) : DiscoverViewEvent
    object PodcastScreenShown : DiscoverViewEvent
    data class ChangeSubscription(val podcast: PodcastUiModel) : DiscoverViewEvent
    object RefreshTrendingPodcasts : DiscoverViewEvent
    object ShowSearchScreen : DiscoverViewEvent
    object SearchScreenShown : DiscoverViewEvent
}
