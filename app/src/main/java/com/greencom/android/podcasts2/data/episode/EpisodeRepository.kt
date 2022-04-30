package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.domain.episode.Episode
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val remoteDataSource: EpisodeRemoteDataSource,
    private val localDataSource: EpisodeLocalDataSource,
) {

    suspend fun loadEpisodesForPodcastById(podcastId: Long, since: Long, max: Int) {
        val entities = remoteDataSource.getEpisodesForPodcastById(
            podcastId = podcastId,
            since = since,
            max = max,
        )
        localDataSource.saveEpisodes(entities)
    }

    suspend fun getEpisodeById(id: Long): Episode? {
        return localDataSource.getEpisodeById(id)
    }

}
