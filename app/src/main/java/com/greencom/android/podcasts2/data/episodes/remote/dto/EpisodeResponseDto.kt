package com.greencom.android.podcasts2.data.episodes.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponseDto(

    @SerialName("id")
    val id: Long? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("link")
    val link: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("guid")
    val guid: String? = null,

    @SerialName("datePublished")
    val datePublished: Long? = null,

    @SerialName("enclosureUrl")
    val enclosureUrl: String? = null,

    @SerialName("enclosureLength")
    val enclosureLength: Long? = null,

    @SerialName("duration")
    val duration: Long? = null, // TODO: seconds or minutes?

    @SerialName("explicit")
    val explicit: Int? = null,

    @SerialName("episode")
    val episode: Int? = null,

    @SerialName("episodeType")
    val episodeType: String? = null,

    @SerialName("season")
    val season: Int? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("feedImage")
    val feedImage: String? = null,

    @SerialName("feedId")
    val feedId: Long? = null,

    @SerialName("feedLanguage")
    val feedLanguage: String? = null,

    @SerialName("chaptersUrl")
    val chaptersUrl: String? = null,

)
