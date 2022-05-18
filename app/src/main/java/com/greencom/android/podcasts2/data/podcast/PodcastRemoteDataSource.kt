package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.category.toApiCategoriesString
import com.greencom.android.podcasts2.data.podcast.remote.PodcastService
import com.greencom.android.podcasts2.domain.category.Category
import javax.inject.Inject

class PodcastRemoteDataSource @Inject constructor(
    private val service: PodcastService,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ) {
        val dto = service.getTrendingPodcasts(
            max = max,
            inCategories = inCategories.toApiCategoriesString(),
            notInCategories = notInCategories.toApiCategoriesString(),
        )
    }

}
