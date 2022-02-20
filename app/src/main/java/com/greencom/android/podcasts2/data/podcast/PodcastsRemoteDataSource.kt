package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.remote.PodcastsService
import com.greencom.android.podcasts2.domain.category.ICategory
import com.greencom.android.podcasts2.domain.category.toCategoriesString
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.language.toLanguagesString
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import javax.inject.Inject

class PodcastsRemoteDataSource @Inject constructor(
    private val podcastsService: PodcastsService,
) {

    suspend fun getTrendingPodcasts(
        max: Int,
        languages: List<Language>,
        inCategories: List<ICategory>,
        notInCategories: List<ICategory>,
    ): List<TrendingPodcast> {
        val dto = podcastsService.getTrendingPodcasts(
            max = max,
            languages = languages.toLanguagesString(),
            inCategories = inCategories.toCategoriesString(),
            notInCategories = notInCategories.toCategoriesString(),
        )
        return dto.toDomain()
    }

}
