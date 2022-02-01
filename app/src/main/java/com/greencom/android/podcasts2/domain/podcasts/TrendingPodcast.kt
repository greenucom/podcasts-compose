package com.greencom.android.podcasts2.domain.podcasts

import com.greencom.android.podcasts2.domain.categories.Category

data class TrendingPodcast(
    val id: Long,
    val url: String,
    val title: String,
    val description: String,
    val author: String,
    val image: String,
    val newestItemPublishedTime: Long,
    val trendScore: Int,
    val language: String,
    val categories: List<Category>,
)
