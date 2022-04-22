package com.greencom.android.podcasts2.data.episode.local

import androidx.room.ColumnInfo
import com.greencom.android.podcasts2.domain.episode.SerialNumber

data class SerialNumberEntity(

    @ColumnInfo(name = "season")
    val season: Int,

    @ColumnInfo(name = "episode")
    val episode: Int,

) {

    fun toSerialNumber(): SerialNumber = SerialNumber(
        season = if (season == MISSING_VALUE) null else season,
        episode = if (episode == MISSING_VALUE) null else episode,
    )

    companion object {
        const val MISSING_VALUE = -1
    }

}
