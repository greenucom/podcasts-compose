package com.greencom.android.podcasts2.data.episode.remote.dto

import com.greencom.android.podcasts2.data.episode.local.EpisodeTypeEntity

@JvmInline
value class EpisodeTypeDto private constructor(private val value: String) {

    fun toEpisodeTypeEntity(): EpisodeTypeEntity = when (value) {
        FULL -> EpisodeTypeEntity.FULL
        BONUS -> EpisodeTypeEntity.BONUS
        TRAILER -> EpisodeTypeEntity.TRAILER
        else -> error("Unknown episode type $value")
    }

    companion object {
        fun fromString(string: String): EpisodeTypeDto = EpisodeTypeDto(string)

        private const val FULL = "full"
        private const val BONUS = "bonus"
        private const val TRAILER = "trailer"
    }

}
