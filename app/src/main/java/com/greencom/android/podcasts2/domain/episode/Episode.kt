package com.greencom.android.podcasts2.domain.episode

import kotlin.time.Duration

data class Episode(
    val id: Long,
    val title: String,
    val description: String,
    val date: Long,
    val seasonNumber: Int,
    val episodeNumber: Int,
    val type: Type,
    val explicit: Boolean,
    val audioUrl: String,
    val audioSizeInBytes: Long,
    val audioDuration: Duration,
    val chaptersUrl: String,
    val imageUrl: String,
    val podcastId: Long,
) {

    enum class Type {
        Full, Bonus, Trailer
    }

}
