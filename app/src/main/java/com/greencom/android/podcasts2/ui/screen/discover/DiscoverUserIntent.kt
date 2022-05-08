package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.mvi.Intent

sealed interface DiscoverUserIntent : Intent {
    class ToggleSelectableCategory(val category: Category) : DiscoverUserIntent
    class ClickPodcast(val podcast: Podcast) : DiscoverUserIntent
    class ChangeSubscription(val podcast: Podcast) : DiscoverUserIntent
    object RefreshTrendingPodcasts : DiscoverUserIntent
    object PodcastClicked : DiscoverUserIntent
}
