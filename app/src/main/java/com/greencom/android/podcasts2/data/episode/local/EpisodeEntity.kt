package com.greencom.android.podcasts2.data.episode.local

import androidx.room.*
import com.greencom.android.podcasts2.data.common.DurationTypeConverter
import com.greencom.android.podcasts2.data.common.SizeTypeConverter
import com.greencom.android.podcasts2.data.episode.remote.dto.EpisodeTypeDto
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.utils.Size
import kotlin.time.Duration

@Entity(tableName = "episodes")
@TypeConverters(
    SizeTypeConverter::class,
    DurationTypeConverter::class,
)
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
    val type: String,

    @ColumnInfo(name = "explicit")
    val explicit: Boolean,

    @ColumnInfo(name = "audio_url")
    val audioUrl: String,

    @ColumnInfo(name = "audio_size")
    val audioSize: Size,

    @ColumnInfo(name = "audio_duration")
    val audioDuration: Duration,

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
        type = EpisodeTypeDto(type).toEpisodeType(),
        explicit = explicit,
        audioUrl = audioUrl,
        audioSize = audioSize,
        audioDuration = audioDuration,
        chaptersUrl = chaptersUrl,
        imageUrl = imageUrl,
        podcastId = podcastId,
    )

}
