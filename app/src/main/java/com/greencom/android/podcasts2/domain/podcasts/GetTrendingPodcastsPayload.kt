package com.greencom.android.podcasts2.domain.podcasts

import com.greencom.android.podcasts2.domain.categories.Category

data class GetTrendingPodcastsPayload(
    val max: Int,
    val language: String,
    val inCategories: List<Category>,
    val notInCategories: List<Category>,
)
