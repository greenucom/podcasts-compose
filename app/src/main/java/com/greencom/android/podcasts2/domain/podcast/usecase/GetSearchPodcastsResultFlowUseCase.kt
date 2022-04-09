package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.SimpleUseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchPodcastsResultFlowUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository,
) : SimpleUseCase<Unit, Flow<List<Podcast>>>() {

    override fun invoke(params: Unit): Flow<List<Podcast>> {
        return podcastRepository.lastSearchPodcastsResult
    }

}
