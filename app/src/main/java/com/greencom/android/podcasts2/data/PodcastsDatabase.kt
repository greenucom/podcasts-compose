package com.greencom.android.podcasts2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greencom.android.podcasts2.data.episode.local.EpisodeDao
import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntityTemp

@Database(
    entities = [
        PodcastEntity::class,
        PodcastEntityTemp::class,
        EpisodeEntity::class,
    ],
    version = 8,
)
abstract class PodcastsDatabase : RoomDatabase() {

    abstract fun podcastDao(): PodcastDao

    abstract fun episodeDao(): EpisodeDao

}
