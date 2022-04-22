package com.greencom.android.podcasts2.data.episode.remote

import com.greencom.android.podcasts2.data.episode.remote.dto.GetEpisodesForPodcastByIdResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {

    @GET("episodes/byfeedid?fulltext")
    suspend fun getEpisodesForPodcastById(

        @Query("id")
        podcastId: Long,

        @Query("since")
        since: Long,

        @Query("max")
        max: Int,

    ): GetEpisodesForPodcastByIdResponseDto

}
