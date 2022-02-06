package com.greencom.android.podcasts2.domain.podcasts.usecases

import com.greencom.android.podcasts2.clean.UseCase
import com.greencom.android.podcasts2.data.podcasts.PodcastsRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ChangeSubscriptionUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastsRepository: PodcastsRepository,
) : UseCase<IPodcast, Unit>(dispatcher) {

    override suspend fun execute(params: IPodcast) {
        podcastsRepository.updatePodcast(params)
    }

}