package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.data.episode.remote.EpisodeService
import javax.inject.Inject

class EpisodeRemoteDataSource @Inject constructor(
    private val episodeService: EpisodeService,
) {

}
