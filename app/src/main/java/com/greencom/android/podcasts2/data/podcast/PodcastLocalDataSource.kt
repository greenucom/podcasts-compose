package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import javax.inject.Inject

class PodcastLocalDataSource @Inject constructor(
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
