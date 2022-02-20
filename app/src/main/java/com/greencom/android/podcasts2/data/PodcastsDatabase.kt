package com.greencom.android.podcasts2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntity
import com.greencom.android.podcasts2.data.podcast.local.PodcastEntityTemp

@Database(
    entities = [
        PodcastEntity::class,
        PodcastEntityTemp::class,
    ],
    version = 1,
)
abstract class PodcastsDatabase : RoomDatabase() {

    abstract fun podcastDao(): PodcastDao

}
