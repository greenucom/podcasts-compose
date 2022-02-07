package com.greencom.android.podcasts2.domain.podcasts.usecases

import com.greencom.android.podcasts2.clean.UseCase
import com.greencom.android.podcasts2.data.podcasts.PodcastsRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.domain.podcasts.payload.GetTrendingPodcastsPayload
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTrendingPodcastsUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastsRepository: PodcastsRepository,
) : UseCase<GetTrendingPodcastsPayload, List<TrendingPodcast>>(dispatcher) {

    override suspend fun execute(params: GetTrendingPodcastsPayload): List<TrendingPodcast> {
        val (inCategories, max, language, notInCategories) = params
        return podcastsRepository.getTrendingPodcasts(
            max = max,
            language = language,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

}
