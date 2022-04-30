package com.greencom.android.podcasts2.data.episode.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.utils.Size.Companion.bytes
import kotlin.time.Duration.Companion.milliseconds

@Entity(tableName = "Episode")
data class EpisodeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "date_unix")
    val dateUnix: Long,

    @Embedded(prefix = "serial_number_")
    val serialNumber: SerialNumberEntity,

    @ColumnInfo(name = "type")
    val type: Episode.Type,

    @ColumnInfo(name = "explicit")
    val explicit: Boolean,

    @ColumnInfo(name = "audio_url")
    val audioUrl: String,

    @ColumnInfo(name = "audio_size_in_bytes")
    val audioSizeInBytes: Long,

    @ColumnInfo(name = "audio_duration_in_milliseconds")
    val audioDurationInMilliseconds: Long,

    @ColumnInfo(name = "chapters_url")
    val chaptersUrl: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "podcast_id")
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
