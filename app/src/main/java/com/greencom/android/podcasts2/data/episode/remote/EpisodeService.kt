package com.greencom.android.podcasts2.data.episode.remote

import androidx.annotation.IntRange
import com.greencom.android.podcasts2.data.episode.remote.dto.GetEpisodesByPodcastIdResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {

    @GET("episodes/byfeedid")
    fun getEpisodesByPodcastId(
        @Query("id") id: Long,
        @Query("since") since: Long,

        @Query("max")
        @IntRange(from = 1, to = 1000)
        max: Int,
    ): GetEpisodesByPodcastIdResponseDto

}
