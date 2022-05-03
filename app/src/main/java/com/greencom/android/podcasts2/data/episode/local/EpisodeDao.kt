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
        SELECT episode_id, episode_title, episode_description, episode_date_unix, 
            episode_serial_number, episode_type, episode_explicit, episode_audio_url, 
            episode_audio_size_in_bytes, episode_audio_duration_in_milliseconds, 
            episode_chapters_url, episode_image_url, episode_podcast_id
        FROM Episode
        WHERE episode_id = :id
    """)
    abstract suspend fun getEpisodeById(id: Long): EpisodeEntity?

}
