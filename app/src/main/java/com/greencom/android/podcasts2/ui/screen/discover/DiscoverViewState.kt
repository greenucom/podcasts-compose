package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import com.greencom.android.podcasts2.ui.model.podcast.PodcastUiModel

sealed interface DiscoverViewState : State {

    object InitialLoading : DiscoverViewState

    @Immutable
    data class Success(
        val selectableTrendingCategories: List<SelectableItem<CategoryUiModel>>,
        val trendingPodcasts: List<PodcastUiModel>,
    ) : DiscoverViewState

    object Error : DiscoverViewState

}
