package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import com.greencom.android.podcasts2.data.episode.remote.EpisodeService
import javax.inject.Inject

class EpisodeRemoteDataSource @Inject constructor(
    private val apiService: EpisodeService,
) {

    suspend fun getEpisodesForPodcastById(
        podcastId: Long,
        since: Long,
        max: Int,
    ): List<EpisodeEntity> {
        val dto = apiService.getEpisodesForPodcastById(
            podcastId = podcastId,
            since = since,
            max = max,
        )
        return dto.toEpisodeEntities()
    }

}
