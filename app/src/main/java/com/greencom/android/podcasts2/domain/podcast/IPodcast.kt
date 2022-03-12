package com.greencom.android.podcasts2.domain.podcast

import com.greencom.android.podcasts2.domain.category.Category

abstract class IPodcast(
    open val id: Long,
    open val title: String,
    open val description: String,
    open val author: String,
    open val image: String,
    open val categories: List<Category>,
    open val isSubscribed: Boolean,
)
