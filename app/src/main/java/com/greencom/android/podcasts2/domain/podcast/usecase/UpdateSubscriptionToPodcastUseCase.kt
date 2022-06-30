package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.UseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

class UpdateSubscriptionToPodcastUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : UseCase<Podcast, Unit>(dispatcher) {

    override suspend fun execute(params: Podcast) {
        Timber.d("Update subscription to podcast: $params")
        podcastRepository.updateSubscriptionToPodcast(params)
    }

}
