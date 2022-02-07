package com.greencom.android.podcasts2.data.podcasts

import com.greencom.android.podcasts2.domain.categories.ICategory
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val remoteDataSource: PodcastsRemoteDataSource,
    private val localDataSource: PodcastsLocalDataSource,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        language: String,
        inCategories: List<ICategory>,
        notInCategories: List<ICategory>,
    ): List<TrendingPodcast> {
        return remoteDataSource.getTrendingPodcasts(
            max = max,
            language = language,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

    suspend fun updatePodcast(podcast: IPodcast) {
        localDataSource.update(podcast)
    }

}
