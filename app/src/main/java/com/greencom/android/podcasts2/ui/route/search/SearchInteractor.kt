package com.greencom.android.podcasts2.ui.route.search

import com.greencom.android.podcasts2.domain.podcast.usecase.SearchPodcastsUseCase
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    val searchPodcasts: SearchPodcastsUseCase,
)
