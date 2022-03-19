package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.podcast.Podcast
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val remoteDataSource: PodcastRemoteDataSource,
    private val localDataSource: PodcastLocalDataSource,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        languages: List<Language>,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): List<Podcast> {
        return remoteDataSource.getTrendingPodcasts(
            max = max,
            languages = languages,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

    suspend fun updatePodcast(podcast: Podcast) {
        localDataSource.insert(podcast)
    }

}
