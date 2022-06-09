package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val localDataSource: PodcastLocalDataSource,
    private val remoteDataSource: PodcastRemoteDataSource,
) {

    fun getTrendingPodcasts(
        max: Int,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): Flow<List<Podcast>> {
        val podcastsFlow = flow {
            val podcasts = remoteDataSource.getTrendingPodcasts(
                max = max,
                inCategories = inCategories,
                notInCategories = notInCategories,
            )
            emit(podcasts)
        }
        return combine(
            podcastsFlow,
            localDataSource.userSubscriptionsIds,
        ) { podcasts, userSubscriptionsIds ->
            podcasts.map { it.copy(isUserSubscribed = it.id in userSubscriptionsIds) }
        }
    }

}
