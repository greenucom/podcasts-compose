package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val localDataSource: PodcastLocalDataSource,
    private val remoteDataSource: PodcastRemoteDataSource,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): Flow<List<Podcast>> {
        val trendingPodcasts = remoteDataSource.getTrendingPodcasts(
            max = max,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
        return localDataSource.userSubscriptionsIds.map { userSubscriptionsIds ->
            trendingPodcasts.map { it.copy(isUserSubscribed = it.id in userSubscriptionsIds) }
        }
    }

}
