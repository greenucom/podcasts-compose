package com.greencom.android.podcasts2.data.podcasts.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PodcastResponseDto(
    
    @SerialName("id")
    val id: Long? = null,
    
    @SerialName("title")
    val title: String? = null,
    
    @SerialName("url")
    val url: String? = null,

    @SerialName("link")
    val link: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("author")
    val author: String? = null,

    @SerialName("ownerName")
    val ownerName: String? = null,
    
    @SerialName("image")
    val image: String? = null,
    
    @SerialName("artwork")
    val artwork: String? = null,
    
    @SerialName("lastUpdateTime")
    val lastUpdateTime: Long? = null,
    
    @SerialName("language")
    val language: String? = null,

    @SerialName("categories")
    val categories: Map<Int, String>? = null,

)
