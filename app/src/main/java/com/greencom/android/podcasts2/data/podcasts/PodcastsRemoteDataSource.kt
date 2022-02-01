package com.greencom.android.podcasts2.data.podcasts

import com.greencom.android.podcasts2.data.podcasts.remote.PodcastsService
import com.greencom.android.podcasts2.domain.categories.toCategoriesString
import com.greencom.android.podcasts2.domain.podcasts.GetTrendingPodcastsPayload
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import javax.inject.Inject

class PodcastsRemoteDataSource @Inject constructor(
    private val podcastsService: PodcastsService,
) {

    suspend fun getTrendingPodcasts(payload: GetTrendingPodcastsPayload): List<TrendingPodcast> {
        val dto = podcastsService.getTrendingPodcasts(
            max = payload.max,
            language = payload.language,
            inCategories = payload.inCategories.toCategoriesString(),
            notInCategories = payload.notInCategories.toCategoriesString(),
        )
        return dto.toDomain()
    }

}
