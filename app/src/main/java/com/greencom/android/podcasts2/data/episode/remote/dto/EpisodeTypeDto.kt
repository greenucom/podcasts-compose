package com.greencom.android.podcasts2.data.episode.remote.dto

import com.greencom.android.podcasts2.domain.episode.Episode

@JvmInline
value class EpisodeTypeDto(private val value: String) {

    fun toEpisodeType(): Episode.Type = when (value) {
        FULL -> Episode.Type.Full
        BONUS -> Episode.Type.Bonus
        TRAILER -> Episode.Type.Trailer
        else -> throw IllegalStateException("Illegal episode type $value")
    }

    companion object {
        private const val FULL = "full"
        private const val BONUS = "bonus"
        private const val TRAILER = "trailer"
    }

}
