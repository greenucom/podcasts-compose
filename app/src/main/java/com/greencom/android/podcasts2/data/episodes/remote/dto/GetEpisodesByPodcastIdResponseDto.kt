package com.greencom.android.podcasts2.data.episodes.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetEpisodesByPodcastIdResponseDto(

    @SerialName("status")
    val status: Boolean? = null,

    @SerialName("count")
    val count: Int? = null,

    @SerialName("items")
    val episodes: List<EpisodeDto>? = null,

)
