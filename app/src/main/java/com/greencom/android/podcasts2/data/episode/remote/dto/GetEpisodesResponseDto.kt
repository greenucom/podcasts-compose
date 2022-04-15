package com.greencom.android.podcasts2.data.episode.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetEpisodesResponseDto(

    @SerialName("items")
    val episodes: List<GetEpisodesItemDto>? = null,

    @SerialName("count")
    val count: Int? = null,

)

@Serializable
data class GetEpisodesItemDto(
    
    @SerialName("id")
    val id: Long? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("datePublished")
    val date: Long? = null,

    @SerialName("season")
    val seasonNumber: Int? = null,

    @SerialName("episode")
    val episodeNumber: Int? = null,

    @SerialName("episodeType")
    val type: String? = null,

    @SerialName("explicit")
    val explicit: Int? = null,

    @SerialName("enclosureUrl")
    val audioUrl: String? = null,

    @SerialName("enclosureLength")
    val audioSizeInBytes: Long? = null,

    @SerialName("audioDuration")
    val audioDurationInSeconds: Int? = null,

    @SerialName("chaptersUrl")
    val chaptersUrl: String? = null,

    @SerialName("image")
    val imageUrl: String? = null,

    @SerialName("podcastId")
    val podcastId: Long? = null,

)
