package com.greencom.android.podcasts2.data.episode

import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val remoteDataSource: EpisodesRemoteDataSource,
    private val localDataSource: EpisodesLocalDataSource,
) {

}