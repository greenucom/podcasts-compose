package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.di.ApplicationScope
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastLocalDataSource @Inject constructor(
    private val dao: PodcastDao,
    @ApplicationScope private val applicationScope: CoroutineScope,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val _userSubscriptionsIds = MutableStateFlow(setOf<Long>())
    val userSubscriptionsIds = _userSubscriptionsIds.asStateFlow()

    init {
        loadUserSubscriptionsIds()
    }

    suspend fun updatePodcast(podcast: Podcast) {
        val podcastEntity = PodcastEntity.fromPodcast(podcast)
        dao.update(podcastEntity)
    }

    private fun loadUserSubscriptionsIds() = applicationScope.launch(ioDispatcher) {
        val userSubscriptionsIds = dao.getUserSubscriptionsIds().toSet()
        _userSubscriptionsIds.update { userSubscriptionsIds }
    }

}
