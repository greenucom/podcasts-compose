package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.di.ApplicationScope
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastLocalDataSource @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val dao: PodcastDao,
) {

    private val _podcastSubscriptionIds = MutableStateFlow(setOf<Long>())
    val podcastSubscriptionIds = _podcastSubscriptionIds.asStateFlow()

    init {
        loadPodcastSubscriptionIds()
    }

    private fun loadPodcastSubscriptionIds() {
        applicationScope.launch(Dispatchers.IO) {
            val ids = dao.getPodcastSubscriptionIds().toSet()
            _podcastSubscriptionIds.update { ids }
        }
    }

    private fun updatePodcastSubscriptionIds(podcast: Podcast) {
        if (podcast.isSubscribed) {
            _podcastSubscriptionIds.update { it + podcast.id }
        } else {
            _podcastSubscriptionIds.update { it - podcast.id }
        }
    }

    suspend fun savePodcast(podcast: Podcast) {
        val entity = PodcastEntity.fromPodcast(podcast)
        dao.insert(entity)
        updatePodcastSubscriptionIds(podcast)
    }

    fun getPodcastWithEpisodesByIdFlow(id: Long): Flow<Map<Podcast?, List<Episode>>> {
        return dao.getPodcastWithEpisodesByIdFlow(id)
            .map { podcastToEpisodes ->
                podcastToEpisodes.entries.associate { (podcast, episodes) ->
                    podcast?.toPodcast() to episodes.map { it.toEpisode() }
                }
            }
    }

}
