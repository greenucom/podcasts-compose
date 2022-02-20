package com.greencom.android.podcasts2.domain.category

data class TrendingCategory(
    override val id: Int,
    override val name: String,
    val displayName: String,
) : ICategory(id, name)
