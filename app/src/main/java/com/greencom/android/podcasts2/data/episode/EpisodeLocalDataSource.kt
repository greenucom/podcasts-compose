package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.data.episode.local.EpisodeDao
import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import javax.inject.Inject

class EpisodeLocalDataSource @Inject constructor(
    private val episodeDao: EpisodeDao,
) {

    suspend fun insert(episodeEntities: List<EpisodeEntity>) {
        episodeDao.insert(episodeEntities)
    }

}
