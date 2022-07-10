package com.greencom.android.podcasts2.ui.route.dialog.podcastdescription

import com.greencom.android.podcasts2.domain.podcast.usecase.GetPodcastUseCase
import javax.inject.Inject

class PodcastDescriptionInteractor @Inject constructor(
    val getPodcast: GetPodcastUseCase,
)
