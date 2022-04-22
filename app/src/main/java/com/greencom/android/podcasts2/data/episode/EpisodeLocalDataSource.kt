package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.data.episode.local.EpisodeDao
import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import com.greencom.android.podcasts2.domain.episode.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodeLocalDataSource @Inject constructor(
    private val dao: EpisodeDao,
) {

    suspend fun insert(episodeEntities: List<EpisodeEntity>) {
        dao.insert(episodeEntities)
    }

    suspend fun getEpisodeById(id: Long): Episode {
        return dao.getEpisodeById(id).toEpisode()
    }

    fun getEpisodesForPodcastByIdFlow(podcastId: Long): Flow<List<Episode>> {
        return dao.getEpisodesForPodcastByIdFlow(podcastId)
            .map { list -> list.map { it.toEpisode() } }
    }

}
