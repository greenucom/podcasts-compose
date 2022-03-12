package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.remote.PodcastService
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.category.toCategoriesString
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.language.toLanguagesString
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import javax.inject.Inject

class PodcastRemoteDataSource @Inject constructor(
    private val podcastService: PodcastService,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        languages: List<Language>,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ): List<TrendingPodcast> {
        val dto = podcastService.getTrendingPodcasts(
            max = max,
            languages = languages.toLanguagesString(),
            inCategories = inCategories.toCategoriesString(),
            notInCategories = notInCategories.toCategoriesString(),
        )
        return dto.toDomain()
    }

}
