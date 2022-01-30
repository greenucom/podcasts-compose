package com.greencom.android.podcasts2.data.podcasts.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPodcastsResponseDto(

    @SerialName("status")
    val status: Boolean? = null,

    @SerialName("count")
    val count: Int? = null,

    @SerialName("query")
    val query: String? = null,

    @SerialName("feeds")
    val podcasts: List<PodcastDto>? = null,

)
