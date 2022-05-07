package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.data.episode.local.EpisodeDao
import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import javax.inject.Inject

class EpisodeLocalDataSource @Inject constructor(
    private val dao: EpisodeDao,
) {

    suspend fun saveEpisodes(episodeEntities: List<EpisodeEntity>) {
        dao.insert(episodeEntities)
    }

}
