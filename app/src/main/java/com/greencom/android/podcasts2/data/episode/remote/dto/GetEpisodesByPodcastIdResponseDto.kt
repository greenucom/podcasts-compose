package com.greencom.android.podcasts2.data.episode.remote.dto

import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import com.greencom.android.podcasts2.utils.toBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetEpisodesByPodcastIdResponseDto(
    @SerialName("items") val episodes: List<EpisodeDto>? = null,
    @SerialName("count") val episodeCount: Int? = null,
) {

    fun toEpisodeEntityList(): List<EpisodeEntity> {
        return episodes?.map { it.toEpisodeEntity() } ?: emptyList()
    }

    @Serializable
    data class EpisodeDto(
        @SerialName("id") val id: Long? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("description") val description: String? = null,
        @SerialName("feedId") val podcastId: Long? = null,
        @SerialName("datePublished") val publicationDateUnixSeconds: Long? = null,
        @SerialName("enclosureUrl") val audioUrl: String? = null,
        @SerialName("enclosureLength") val audioSizeInBytes: Long? = null,
        @SerialName("duration") val audioDurationInSeconds: Int? = null,
        @SerialName("explicit") val explicit: Int? = null,
        @SerialName("season") val season: Int? = null,
        @SerialName("episode") val episodeNumber: Int? = null,
        @SerialName("episodeType") val episodeType: String? = null,
        @SerialName("image") val imageUrl: String? = null,
        @SerialName("chaptersUrl") val chaptersUrl: String? = null,
    ) {

        fun toEpisodeEntity(): EpisodeEntity = EpisodeEntity(
            id = checkNotNull(id),
            title = checkNotNull(title),
            description = checkNotNull(description),
            podcastId = checkNotNull(podcastId),
            publicationDateUnixSeconds = checkNotNull(publicationDateUnixSeconds),
            audioUrl = checkNotNull(audioUrl),
            audioSizeInBytes = checkNotNull(audioSizeInBytes),
            audioDurationInSeconds = checkNotNull(audioDurationInSeconds),
            explicit = explicit?.toBoolean() ?: false,
            season = season,
            episodeNumber = episodeNumber,
            episodeType = episodeType?.let { EpisodeTypeDto.fromString(it).toEpisodeTypeEntity() },
            imageUrl = checkNotNull(imageUrl),
            chaptersUrl = chaptersUrl,
        )

    }

}
