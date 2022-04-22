package com.greencom.android.podcasts2.data.episode.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(episodeEntities: List<EpisodeEntity>)

    @Query("""
        SELECT id, title, description, date_unix, serial_number_season, serial_number_episode,
            type, explicit, audio_url, audio_size, audio_duration, chapters_url,
            image_url, podcast_id
        FROM episodes
        WHERE id = :id
    """)
    abstract suspend fun getEpisodeById(id: Long): EpisodeEntity

    @Query("""
        SELECT id, title, description, date_unix, serial_number_season, serial_number_episode,
            type, explicit, audio_url, audio_size, audio_duration, chapters_url,
            image_url, podcast_id
        FROM episodes
        WHERE podcast_id = :podcastId
        ORDER BY date_unix DESC
    """)
    protected abstract fun getEpisodesForPodcastByIdFlowRaw(podcastId: Long): Flow<List<EpisodeEntity>>

    fun getEpisodesForPodcastByIdFlow(podcastId: Long): Flow<List<EpisodeEntity>> =
        getEpisodesForPodcastByIdFlowRaw(podcastId).distinctUntilChanged()

}
