package com.greencom.android.podcasts2.data.episode.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Episodes")
data class EpisodeEntity(
    @PrimaryKey
    @ColumnInfo(name = "episode_id") val id: Long,
    @ColumnInfo(name = "episode_title") val title: String,
    @ColumnInfo(name = "episode_description") val description: String,
    @ColumnInfo(name = "episode_podcast_id") val podcastId: Long,
    @ColumnInfo(name = "episode_publication_date") val publicationDate: Long,
    @ColumnInfo(name = "episode_audio_url") val audioUrl: String,
    @ColumnInfo(name = "episode_audio_size_in_bytes") val audioSizeInBytes: Long,
    @ColumnInfo(name = "episode_audio_duration_in_seconds") val audioDurationInSeconds: Int,
    @ColumnInfo(name = "episode_explicit") val explicit: Boolean,
    @ColumnInfo(name = "episode_season") val season: Int?,
    @ColumnInfo(name = "episode_number") val episodeNumber: Int?,
    @ColumnInfo(name = "episode_type") val episodeType: EpisodeTypeEntity?,
    @ColumnInfo(name = "episode_image_url") val imageUrl: String,
    @ColumnInfo(name = "episode_chapters_url") val chaptersUrl: String?,
)
