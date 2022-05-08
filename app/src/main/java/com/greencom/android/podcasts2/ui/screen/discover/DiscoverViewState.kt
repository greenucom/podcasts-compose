package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.State

data class DiscoverViewState(
    val recommendedPodcastsState: DiscoverRecommendedPodcastsState =
        DiscoverRecommendedPodcastsState.Loading,
    val selectableCategories: List<SelectableItem<Category>> = emptyList(),
    val trendingPodcastsState: DiscoverTrendingPodcastsState =
        DiscoverTrendingPodcastsState.Loading,
    val showPodcastScreen: Podcast? = null,
    val showSearchScreen: Boolean = false,
) : State

sealed interface DiscoverRecommendedPodcastsState {
    object Loading : DiscoverRecommendedPodcastsState
    data class Success(val podcasts: List<Podcast>) : DiscoverRecommendedPodcastsState
}

sealed interface DiscoverTrendingPodcastsState {
    object Loading : DiscoverTrendingPodcastsState
    data class Success(val podcasts: List<Podcast>) : DiscoverTrendingPodcastsState
    object Error : DiscoverTrendingPodcastsState
}
