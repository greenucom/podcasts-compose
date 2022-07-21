package com.greencom.android.podcasts2.data.episode.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetEpisodesByPodcastIdResponseDto(
    @SerialName("items") val items: List<EpisodeDto>? = null,
    @SerialName("count") val count: Int? = null,
) {

    @Serializable
    data class EpisodeDto(
        @SerialName("id") val id: Long? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("description") val description: String? = null,
        @SerialName("datePublished") val publicationDate: Long? = null,
        @SerialName("enclosureUrl") val audioUrl: String? = null,
        @SerialName("enclosureLength") val audioSizeInBytes: Long? = null,
        @SerialName("duration") val audioDurationInSeconds: Int? = null,
        @SerialName("explicit") val explicit: Int? = null,
        @SerialName("season") val season: Int? = null,
        @SerialName("episode") val episodeNumber: Int? = null,
        @SerialName("episodeType") val episodeType: String? = null,
        @SerialName("image") val imageUrl: String? = null,
        @SerialName("chaptersUrl") val chaptersUrl: String? = null,
    )

}
