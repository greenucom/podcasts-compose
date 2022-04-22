package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.di.ApplicationScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastLocalDataSource @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val dao: PodcastDao,
) {

    private val _subscriptionIds = MutableStateFlow(setOf<Long>())
    val subscriptionIds = _subscriptionIds.asStateFlow()

    init {
        loadSubscriptionIds()
    }

    private fun loadSubscriptionIds() {
        applicationScope.launch(Dispatchers.IO) {
            val ids = dao.getSubscriptionIds().toSet()
            _subscriptionIds.update { ids }
        }
    }

    suspend fun insert(podcast: Podcast) {
        val entity = PodcastEntity.fromPodcast(podcast)
        dao.insert(entity)
        updateSubscriptionIds(podcast)
    }

    private fun updateSubscriptionIds(podcast: Podcast) {
        if (podcast.isSubscribed) {
            _subscriptionIds.update { it + podcast.id }
        } else {
            _subscriptionIds.update { it - podcast.id }
        }
    }

}
