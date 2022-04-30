package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.UseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SavePodcastUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : UseCase<Podcast, Unit>(dispatcher) {

    override suspend fun execute(params: Podcast) {
        podcastRepository.insertPodcast(params)
    }

}