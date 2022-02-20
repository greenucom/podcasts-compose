package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.clean.UseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ChangeSubscriptionUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : UseCase<IPodcast, Unit>(dispatcher) {

    override suspend fun execute(params: IPodcast) {
        podcastRepository.updatePodcast(params)
    }

}