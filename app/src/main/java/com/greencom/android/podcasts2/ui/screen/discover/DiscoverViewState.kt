package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.mvi.State

data class DiscoverViewState(
    val recommendedPodcastsState: DiscoverRecommendedPodcastsState =
        DiscoverRecommendedPodcastsState.Loading,
    val selectableCategories: List<SelectableItem<CategoryUiModel>> = emptyList(),
    val trendingPodcastsState: DiscoverTrendingPodcastsState =
        DiscoverTrendingPodcastsState.Loading,
    val showPodcastScreen: PodcastUiModel? = null,
    val showSearchScreen: Boolean = false,
) : State

sealed interface DiscoverRecommendedPodcastsState {
    object Loading : DiscoverRecommendedPodcastsState
    data class Success(val podcasts: List<PodcastUiModel>) : DiscoverRecommendedPodcastsState
}

sealed interface DiscoverTrendingPodcastsState {
    object Loading : DiscoverTrendingPodcastsState
    data class Success(val podcasts: List<PodcastUiModel>) : DiscoverTrendingPodcastsState
    object Error : DiscoverTrendingPodcastsState
}
