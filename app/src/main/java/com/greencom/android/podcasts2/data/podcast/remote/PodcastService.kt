package com.greencom.android.podcasts2.data.podcast.remote

import com.greencom.android.podcasts2.data.podcast.remote.dto.GetTrendingPodcastsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastService {

    @GET("podcasts/trending")
    suspend fun getTrendingPodcasts(
        @Query("max") max: Int,
//        @Query("lang") languages: String, // TODO: Add support for languages
        @Query("cat") inCategories: String,
        @Query("notcat") notInCategories: String,
    ): GetTrendingPodcastsResponseDto

}
