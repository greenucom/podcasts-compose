package com.greencom.android.podcasts2.data.podcast.remote

import com.greencom.android.podcasts2.data.podcast.remote.dto.GetTrendingPodcastsResponseDto
import com.greencom.android.podcasts2.data.podcast.remote.dto.SearchPodcastsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastService {

    @GET("podcasts/trending")
    suspend fun getTrendingPodcasts(

        @Query("max")
        max: Int,

        @Query("lang")
        languages: String,

        @Query("cat")
        inCategories: String,

        @Query("notcat")
        notInCategories: String,

    ): GetTrendingPodcastsResponseDto

    @GET("search/byterm")
    suspend fun searchPodcasts(

        @Query("q")
        query: String,

    ): SearchPodcastsResponseDto

}
