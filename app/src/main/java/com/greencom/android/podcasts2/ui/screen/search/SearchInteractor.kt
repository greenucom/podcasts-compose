package com.greencom.android.podcasts2.ui.screen.search

import com.greencom.android.podcasts2.domain.podcast.usecase.GetSearchPodcastsResultFlowUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.SearchPodcastsUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.UpdatePodcastSubscriptionUseCase
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    val searchPodcastsUseCase: SearchPodcastsUseCase,
    val getSearchPodcastsResultFlowUseCase: GetSearchPodcastsResultFlowUseCase,
    val updatePodcastSubscriptionUseCase: UpdatePodcastSubscriptionUseCase,
)
