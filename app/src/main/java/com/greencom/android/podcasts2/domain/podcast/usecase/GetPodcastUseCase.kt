package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.FlowUseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.Dispatcher
import com.greencom.android.podcasts2.di.PodcastsDispatcher
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPodcastUseCase @Inject constructor(
    @Dispatcher(PodcastsDispatcher.IO) dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository
) : FlowUseCase<GetPodcastUseCase.Params, Podcast>(dispatcher) {

    override fun execute(params: Params): Flow<Result<Podcast>> {
        val (id) = params
        return podcastRepository.getPodcastById(id).map { Result.success(it) }
    }

    data class Params(val podcastId: Long)

}
