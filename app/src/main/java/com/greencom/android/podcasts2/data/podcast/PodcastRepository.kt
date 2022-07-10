package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val localDataSource: PodcastLocalDataSource,
    private val remoteDataSource: PodcastRemoteDataSource,
) {

    suspend fun savePodcast(podcast: Podcast) {
        localDataSource.savePodcast(podcast)
    }

    suspend fun updateSubscriptionToPodcast(podcast: Podcast) {
        localDataSource.updateSubscriptionToPodcast(podcast)
    }

    fun getTrendingPodcasts(
        max: Int,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): Flow<Result<List<Podcast>>> {
        return flow {
            val trendingPodcasts = remoteDataSource.getTrendingPodcasts(
                max = max,
                inCategories = inCategories,
                notInCategories = notInCategories,
            )
            emit(trendingPodcasts)
        }
            .combine(localDataSource.userSubscriptionsIds) { trendingPodcasts, userSubscriptionsIds ->
                trendingPodcasts.map { it.copy(isUserSubscribed = it.id in userSubscriptionsIds) }
            }
            .map { Result.success(it) }
            .catch { e ->
                Timber.e(e, "Exception occurred while receiving trending podcasts")
                emit(Result.failure(e))
            }
    }

}
