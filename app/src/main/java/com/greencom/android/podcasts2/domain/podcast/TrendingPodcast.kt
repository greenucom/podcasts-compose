package com.greencom.android.podcasts2.domain.podcast

import com.greencom.android.podcasts2.domain.category.Category

data class TrendingPodcast(
    override val id: Long,
    override val title: String,
    override val description: String,
    override val author: String,
    override val image: String,
    override val categories: List<Category>,
    override val isSubscribed: Boolean,
) : IPodcast(
    id = id,
    title = title,
    description = description,
    author = author,
    image = image,
    categories = categories,
    isSubscribed = isSubscribed,
)
