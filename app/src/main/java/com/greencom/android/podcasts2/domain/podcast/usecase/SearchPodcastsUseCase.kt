package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.FlowUseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.Dispatcher
import com.greencom.android.podcasts2.di.PodcastsDispatcher
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPodcastsUseCase @Inject constructor(
    @Dispatcher(PodcastsDispatcher.IO) dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : FlowUseCase<SearchPodcastsUseCase.Params, List<Podcast>>(dispatcher) {

    override fun execute(params: Params): Flow<Result<List<Podcast>>> {
        val query = params.query
        return podcastRepository.searchPodcasts(query)
    }

    data class Params(val query: String)

}
