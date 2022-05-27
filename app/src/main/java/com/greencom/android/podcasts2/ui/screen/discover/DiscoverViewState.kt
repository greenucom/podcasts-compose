package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel

@Stable
sealed interface DiscoverViewState : State {

    object InitialLoading : DiscoverViewState

    @Immutable
    data class Success(
        val selectableTrendingCategories: List<SelectableItem<CategoryUiModel>>,
    ) : DiscoverViewState

}
