package com.greencom.android.podcasts2.domain.podcasts

abstract class IPodcast(
    open val id: Long,
    open val title: String,
    open val description: String,
    open val author: String,
    open val image: String,
    open val isSubscribed: Boolean,
)
