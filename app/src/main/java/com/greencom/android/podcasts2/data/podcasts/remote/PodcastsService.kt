package com.greencom.android.podcasts2.data.podcasts.remote

import com.greencom.android.podcasts2.data.podcasts.remote.dto.GetTrendingPodcastsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastsService {

    @GET("podcasts/trending")
    suspend fun getTrendingPodcasts(

        @Query("max")
        max: Int,

        @Query("lang")
        language: String,

        @Query("cat")
        inCategories: String,

        @Query("notcat")
        notInCategories: String,

    ) : GetTrendingPodcastsResponseDto

}
