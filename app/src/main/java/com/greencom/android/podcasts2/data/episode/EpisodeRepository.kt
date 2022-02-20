package com.greencom.android.podcasts2.data.episode

import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val remoteDataSource: EpisodeRemoteDataSource,
    private val localDataSource: EpisodeLocalDataSource,
) {

}
