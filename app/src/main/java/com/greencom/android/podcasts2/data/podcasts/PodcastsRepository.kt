package com.greencom.android.podcasts2.data.podcasts

import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.domain.podcasts.payload.GetTrendingPodcastsPayload
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val remoteDataSource: PodcastsRemoteDataSource,
    private val localDataSource: PodcastsLocalDataSource,
) {

    suspend fun getTrendingPodcasts(payload: GetTrendingPodcastsPayload): List<TrendingPodcast> {
        val podcasts = remoteDataSource.getTrendingPodcasts(payload)
        localDataSource.insert(podcasts)

        val ids = podcasts.map { it.id }
        return localDataSource.getTrendingPodcasts(ids)
    }

}
