package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsUseCase
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    val getTrendingPodcasts: GetTrendingPodcastsUseCase,
)
