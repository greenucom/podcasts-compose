package com.greencom.android.podcasts2.data.episode

import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val localDataSource: EpisodeLocalDataSource,
    private val remoteDataSource: EpisodeRemoteDataSource,
) {

}
