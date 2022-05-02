package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.FlowUseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPodcastWithEpisodesFlowUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : FlowUseCase<GetPodcastWithEpisodesFlowUseCase.Params, Map<Podcast?, List<Episode>>>(dispatcher) {

    override fun execute(params: Params): Flow<Result<Map<Podcast?, List<Episode>>>> {
        return podcastRepository.getPodcastWithEpisodesByIdFlow(params.podcastId)
            .map { Result.success(it) }
    }

    data class Params(val podcastId: Long)

}
