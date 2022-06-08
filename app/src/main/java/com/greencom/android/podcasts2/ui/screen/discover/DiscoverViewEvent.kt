package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.mvi.Event

sealed interface DiscoverViewEvent : Event {
    data class ToggleSelectableTrendingCategory(val category: Category) : DiscoverViewEvent
}
