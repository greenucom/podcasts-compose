package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.domain.episode.Episode
import kotlinx.coroutines.flow.Flow
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
        localDataSource.insert(entities)
    }

    fun getEpisodesForPodcastByIdFlow(podcastId: Long): Flow<List<Episode>> {
        return localDataSource.getEpisodesForPodcastByIdFlow(podcastId)
    }

}
