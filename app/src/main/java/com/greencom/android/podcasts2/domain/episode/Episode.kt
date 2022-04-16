package com.greencom.android.podcasts2.domain.episode

import com.greencom.android.podcasts2.utils.Size
import kotlin.time.Duration

data class Episode(
    val id: Long,
    val title: String,
    val description: String,
    val date: Long,
    val serialNumber: SerialNumber,
    val type: Type,
    val explicit: Boolean,
    val audioUrl: String,
    val audioSize: Size,
    val audioDuration: Duration,
    val chaptersUrl: String,
    val imageUrl: String,
    val podcastId: Long,
) {

    enum class Type {
        Full, Bonus, Trailer
    }

}
