package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.di.DefaultDispatcher
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: PodcastRemoteDataSource,
    private val localDataSource: PodcastLocalDataSource,
) {

    val trendingPodcasts = combine(
        remoteDataSource.trendingPodcasts,
        localDataSource.subscriptionIds,
    ) { trendingPodcasts, subscriptionIds ->
        trendingPodcasts.map { podcast ->
            podcast.copy(isSubscribed = podcast.id in subscriptionIds)
        }
    }.flowOn(defaultDispatcher)

    val lastSearchPodcastsResult = combine(
        remoteDataSource.lastSearchPodcastsResult,
        localDataSource.subscriptionIds,
    ) { searchResult, subscriptionIds ->
        searchResult.map { podcast ->
            podcast.copy(isSubscribed = podcast.id in subscriptionIds)
        }
    }.flowOn(defaultDispatcher)

    suspend fun loadTrendingPodcasts(
        max: Int,
        languages: List<Language>,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ) {
        remoteDataSource.loadTrendingPodcasts(
            max = max,
            languages = languages,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

    suspend fun searchPodcasts(query: String) {
        remoteDataSource.searchPodcasts(query)
    }

    suspend fun updatePodcast(podcast: Podcast) {
        localDataSource.insert(podcast)
    }

    fun getPodcastWithEpisodesByIdFlow(id: Long): Flow<Map<Podcast?, List<Episode>>> {
        return localDataSource.getPodcastWithEpisodesByIdFlow(id)
    }

}
