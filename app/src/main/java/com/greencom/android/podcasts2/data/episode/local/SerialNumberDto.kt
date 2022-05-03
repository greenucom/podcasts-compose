package com.greencom.android.podcasts2.data.episode.local

import com.greencom.android.podcasts2.domain.episode.SerialNumber
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerialNumberDto(

    @SerialName("season")
    val season: Int?,

    @SerialName("episode")
    val episode: Int?,

) {

    fun toSerialNumber(): SerialNumber = SerialNumber(
        season = season,
        episode = episode,
    )

}
