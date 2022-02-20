package com.greencom.android.podcasts2.domain.podcast

data class TrendingPodcast(
    override val id: Long,
    override val title: String,
    override val description: String,
    override val author: String,
    override val image: String,
    override val isSubscribed: Boolean,
) : IPodcast(
    id = id,
    title = title,
    description = description,
    author = author,
    image = image,
    isSubscribed = isSubscribed,
)
