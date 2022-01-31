package com.greencom.android.podcasts2.data.podcasts.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTrendingResponseDto(

    @SerialName("status")
    val status: Boolean? = null,

    @SerialName("feeds")
    val podcasts: List<GetTrendingPodcastResponseDto>? = null,

    @SerialName("count")
    val count: Int? = null,

    @SerialName("max")
    val max: Int? = null,

)

@Serializable
data class GetTrendingPodcastResponseDto(
    
    @SerialName("id")
    val id: Long? = null,
    
    @SerialName("url")
    val url: String? = null,
    
    @SerialName("title")
    val title: String? = null,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("author")
    val author: String? = null,
    
    @SerialName("image")
    val image: String? = null,
    
    @SerialName("newestItemPublishedTime")
    val newestItemPublishedTime: Long? = null,
    
    @SerialName("trendScore")
    val trendScore: Int? = null,

    @SerialName("language")
    val language: String? = null,

    // TODO: Add "categories"
    
)
