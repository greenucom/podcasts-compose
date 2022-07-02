package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val localDataSource: PodcastLocalDataSource,
    private val remoteDataSource: PodcastRemoteDataSource,
) {

    suspend fun updateSubscriptionToPodcast(podcast: Podcast) {
        localDataSource.updateSubscriptionToPodcast(podcast)
    }

    suspend fun getTrendingPodcasts(
        max: Int,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): Flow<Result<List<Podcast>>> {
        return try {
            val trendingPodcasts = remoteDataSource.getTrendingPodcasts(
                max = max,
                inCategories = inCategories,
                notInCategories = notInCategories,
            )
            localDataSource.userSubscriptionsIds
                .map { userSubscriptionsIds ->
                    trendingPodcasts.map { it.copy(isUserSubscribed = it.id in userSubscriptionsIds) }
                }
                .map { Result.success(it) }
        } catch (e: Exception) {
            Timber.e(e, "Exception occurred while receiving trending podcasts")
            flow { emit(Result.failure(e)) }
        }
    }

}
