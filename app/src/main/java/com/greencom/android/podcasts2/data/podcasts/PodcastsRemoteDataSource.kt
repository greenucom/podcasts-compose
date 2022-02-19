package com.greencom.android.podcasts2.data.podcasts

import com.greencom.android.podcasts2.data.podcasts.remote.PodcastsService
import com.greencom.android.podcasts2.domain.categories.ICategory
import com.greencom.android.podcasts2.domain.categories.toCategoriesString
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.language.toLanguagesString
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
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
