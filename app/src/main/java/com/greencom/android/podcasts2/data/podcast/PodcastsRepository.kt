package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.domain.category.ICategory
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val remoteDataSource: PodcastsRemoteDataSource,
    private val localDataSource: PodcastsLocalDataSource,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        languages: List<Language>,
        inCategories: List<ICategory>,
        notInCategories: List<ICategory>,
    ): List<TrendingPodcast> {
        return remoteDataSource.getTrendingPodcasts(
            max = max,
            languages = languages,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

    suspend fun updatePodcast(podcast: IPodcast) {
        localDataSource.update(podcast)
    }

}
