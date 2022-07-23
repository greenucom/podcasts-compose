package com.greencom.android.podcasts2.data.episode.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
abstract class EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(episodes: List<EpisodeEntity>)

}
