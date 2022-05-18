package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import javax.inject.Inject

class PodcastLocalDataSource @Inject constructor(
    private val dao: PodcastDao,
) {

}
