package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.category.toApiCategoriesString
import com.greencom.android.podcasts2.data.podcast.remote.PodcastService
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import javax.inject.Inject

class PodcastRemoteDataSource @Inject constructor(
    private val podcastService: PodcastService,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): List<Podcast> {
        val dto = podcastService.getTrendingPodcasts(
            max = max,
            inCategories = inCategories.toApiCategoriesString(),
            notInCategories = notInCategories.toApiCategoriesString(),
        )
        return dto.toPodcasts()
    }

    suspend fun searchPodcasts(query: String): List<Podcast> {
        val dto = podcastService.searchPodcasts(query)
        return dto.toPodcasts()
    }

}
