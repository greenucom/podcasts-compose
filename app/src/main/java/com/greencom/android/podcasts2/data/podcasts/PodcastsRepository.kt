package com.greencom.android.podcasts2.data.podcasts

import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.domain.podcasts.payload.GetTrendingPodcastsPayload
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val remoteDataSource: PodcastsRemoteDataSource,
    private val localDataSource: PodcastsLocalDataSource,
) {

    suspend fun getTrendingPodcasts(payload: GetTrendingPodcastsPayload): List<TrendingPodcast> {
        return remoteDataSource.getTrendingPodcasts(payload)
    }

    suspend fun updatePodcast(podcast: IPodcast) {
        localDataSource.update(podcast)
    }

}
