package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.State

@Stable
sealed interface DiscoverViewState : State {

    object InitialLoading : DiscoverViewState

    @Immutable
    data class Success(
        val selectableTrendingCategories: List<SelectableItem<Category>>,
    ) : DiscoverViewState

}
