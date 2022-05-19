package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.State

@Immutable
sealed interface DiscoverViewState : State {

    @Immutable
    object InitialLoading : DiscoverViewState

    @Immutable
    data class Success(
        val selectableTrendingCategories: List<SelectableItem<Category>>,
        val trendingPodcasts: List<Podcast>,
    ) : DiscoverViewState

}
