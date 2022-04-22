package com.greencom.android.podcasts2.ui.screen.podcast

import com.greencom.android.podcasts2.domain.podcast.usecase.GetPodcastWithEpisodesFlowUseCase
import javax.inject.Inject

class PodcastInteractor @Inject constructor(
    val getPodcastWithEpisodesFlowUseCase: GetPodcastWithEpisodesFlowUseCase,
)
