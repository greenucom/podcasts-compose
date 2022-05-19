package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.UseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTrendingPodcastsUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : UseCase<GetTrendingPodcastsUseCase.Params, List<Podcast>>(dispatcher) {

    override suspend fun execute(params: Params): List<Podcast> {
        val (max, inCategories) = params
        return podcastRepository.getTrendingPodcasts(
            max = max,
            inCategories = inCategories,
            notInCategories = emptyList(),
        )
    }

    data class Params(val max: Int, val inCategories: List<Category>)

}
