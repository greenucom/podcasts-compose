package com.greencom.android.podcasts2.data.podcasts

import com.greencom.android.podcasts2.data.podcasts.local.PodcastDao
import com.greencom.android.podcasts2.data.podcasts.local.PodcastEntity
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import javax.inject.Inject

class PodcastsLocalDataSource @Inject constructor(
    private val podcastDao: PodcastDao,
) {

    suspend fun insert(podcasts: List<IPodcast>) {
        val entities = podcasts.map { PodcastEntity.fromDomain(it) }
        podcastDao.insert(entities)
    }

    suspend fun update(podcast: IPodcast) {
        val entity = PodcastEntity.fromDomain(podcast)
        podcastDao.update(entity)
    }

}
