package com.greencom.android.podcasts2.domain.podcasts

import com.greencom.android.podcasts2.domain.categories.Category

data class TrendingPodcast(
    override val id: Long,
    override val url: String,
    override val title: String,
    override val description: String,
    override val author: String,
    override val image: String,
    val newestItemPublishedTime: Long,
    val trendScore: Int,
    override val language: String,
    override val categories: List<Category>,
) : IPodcast(
    id = id,
    url = url,
    title = title,
    description = description,
    author = author,
    image = image,
    language = language,
    categories = categories,
)
