package com.greencom.android.podcasts2.data.episodes

import com.greencom.android.podcasts2.data.episodes.remote.EpisodesService
import javax.inject.Inject

class EpisodesRemoteDataSource @Inject constructor(
    private val episodesService: EpisodesService,
) {

}
