package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.ui.common.mvi.Event
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel

sealed interface DiscoverViewEvent : Event {
    data class ToggleSelectableTrendingCategory(val category: CategoryUiModel) : DiscoverViewEvent
}
