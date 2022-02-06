package com.greencom.android.podcasts2.domain.podcasts

import com.greencom.android.podcasts2.domain.categories.ICategory

abstract class IPodcast(
    open val id: Long,
    open val url: String,
    open val title: String,
    open val description: String,
    open val author: String,
    open val image: String,
    open val language: String,
    open val categories: List<ICategory>,
)
