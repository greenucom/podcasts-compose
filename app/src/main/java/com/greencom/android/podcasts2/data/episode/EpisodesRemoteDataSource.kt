package com.greencom.android.podcasts2.data.episode

import com.greencom.android.podcasts2.data.episode.remote.EpisodesService
import javax.inject.Inject

class EpisodesRemoteDataSource @Inject constructor(
    private val episodesService: EpisodesService,
) {

}
