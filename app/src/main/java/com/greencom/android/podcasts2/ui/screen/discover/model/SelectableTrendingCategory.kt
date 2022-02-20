package com.greencom.android.podcasts2.ui.screen.discover.model

import com.greencom.android.podcasts2.domain.category.TrendingCategory

data class SelectableTrendingCategory(
    val isSelected: Boolean,
    val category: TrendingCategory,
)
