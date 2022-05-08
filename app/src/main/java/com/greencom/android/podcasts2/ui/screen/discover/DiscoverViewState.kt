package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.mvi.State

@Immutable
data class DiscoverViewState(
    val recommendedPodcastsState: DiscoverRecommendedPodcastsState =
        DiscoverRecommendedPodcastsState.Loading,
    val selectableCategories: List<SelectableItem<CategoryUiModel>> = emptyList(),
    val trendingPodcastsState: DiscoverTrendingPodcastsState =
        DiscoverTrendingPodcastsState.Loading,
    val showPodcastScreen: PodcastUiModel? = null,
    val showSearchScreen: Boolean = false,
) : State

@Immutable
sealed interface DiscoverRecommendedPodcastsState {

    @Immutable
    object Loading : DiscoverRecommendedPodcastsState

    @Immutable
    data class Success(val podcasts: List<PodcastUiModel>) : DiscoverRecommendedPodcastsState

}

@Immutable
sealed interface DiscoverTrendingPodcastsState {

    @Immutable
    object Loading : DiscoverTrendingPodcastsState

    @Immutable
    data class Success(val podcasts: List<PodcastUiModel>) : DiscoverTrendingPodcastsState

    @Immutable
    object Error : DiscoverTrendingPodcastsState

}
