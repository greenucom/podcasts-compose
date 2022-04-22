package com.greencom.android.podcasts2.data.episode

import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val remoteDataSource: EpisodeRemoteDataSource,
    private val localDataSource: EpisodeLocalDataSource,
) {

    suspend fun getEpisodesForPodcastById(podcastId: Long, since: Long, max: Int) {
        val entities = remoteDataSource.getEpisodesForPodcastById(
            podcastId = podcastId,
            since = since,
            max = max,
        )
        localDataSource.insert(entities)
    }

}
