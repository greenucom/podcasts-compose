package com.greencom.android.podcasts2.data.episode.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(episodeEntities: List<EpisodeEntity>)

    @Query("""
        SELECT id, title, description, date_unix, serial_number_season, serial_number_episode, 
            type, explicit, audio_url, audio_size_in_bytes, audio_duration_in_milliseconds, 
            chapters_url, image_url, podcast_id
        FROM Episode
        WHERE id = :id
    """)
    abstract suspend fun getEpisodeById(id: Long): EpisodeEntity?

}
