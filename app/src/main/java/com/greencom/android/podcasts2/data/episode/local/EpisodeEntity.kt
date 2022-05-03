package com.greencom.android.podcasts2.data.episode.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.utils.Size.Companion.bytes
import kotlin.time.Duration.Companion.milliseconds

@Entity(tableName = "Episode")
@TypeConverters(SerialNumberDtoTypeConverter::class)
data class EpisodeEntity(

    @PrimaryKey
    @ColumnInfo(name = "episode_id")
    val id: Long,

    @ColumnInfo(name = "episode_title")
    val title: String,

    @ColumnInfo(name = "episode_description")
    val description: String,

    @ColumnInfo(name = "episode_date_unix")
    val dateUnix: Long,

    @ColumnInfo(name = "episode_serial_number")
    val serialNumber: SerialNumberDto,

    @ColumnInfo(name = "episode_type")
    val type: Episode.Type,

    @ColumnInfo(name = "episode_explicit")
    val explicit: Boolean,

    @ColumnInfo(name = "episode_audio_url")
    val audioUrl: String,

    @ColumnInfo(name = "episode_audio_size_in_bytes")
    val audioSizeInBytes: Long,

    @ColumnInfo(name = "episode_audio_duration_in_milliseconds")
    val audioDurationInMilliseconds: Long,

    @ColumnInfo(name = "episode_chapters_url")
    val chaptersUrl: String,

    @ColumnInfo(name = "episode_image_url")
    val imageUrl: String,

    @ColumnInfo(name = "episode_podcast_id")
    val podcastId: Long,

) {

    fun toEpisode() = Episode(
        id = id,
        title = title,
        description = description,
        dateUnix = dateUnix,
        serialNumber = serialNumber.toSerialNumber(),
        type = type,
        explicit = explicit,
        audioUrl = audioUrl,
        audioSize = audioSizeInBytes.bytes,
        audioDuration = audioDurationInMilliseconds.milliseconds,
        chaptersUrl = chaptersUrl,
        imageUrl = imageUrl,
        podcastId = podcastId,
    )

}
