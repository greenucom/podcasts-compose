package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.di.ApplicationScope
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastLocalDataSource @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val podcastDao: PodcastDao,
) {

    private val subscriptionIds = MutableStateFlow(setOf<Long>())

    init {
        loadSubscriptionIds()
    }

    private fun loadSubscriptionIds() {
        applicationScope.launch(Dispatchers.IO) {
            val ids = podcastDao.getSubscriptionIds().toSet()
            subscriptionIds.update { ids }
        }
    }

    suspend fun insert(podcasts: List<IPodcast>) {
        val entities = podcasts.map { PodcastEntity.fromDomain(it) }
        podcastDao.insert(entities)
    }

    suspend fun update(podcast: IPodcast) {
        val entity = PodcastEntity.fromDomain(podcast)
        podcastDao.insert(entity)
        updateSubscription(podcast)
    }

    private fun updateSubscription(podcast: IPodcast) {
        if (podcast.isSubscribed) {
            subscriptionIds.update { it + podcast.id }
        } else {
            subscriptionIds.update { it - podcast.id }
        }
    }

}
