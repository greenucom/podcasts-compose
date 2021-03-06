package com.greencom.android.podcasts2.domain.podcast

import com.greencom.android.podcasts2.domain.category.Category

data class Podcast(
    val id: Long,
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val categories: List<Category>,
    val isUserSubscribed: Boolean,
)
