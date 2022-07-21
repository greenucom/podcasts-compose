package com.greencom.android.podcasts2.data.episode.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(episodes: List<EpisodeEntity>)

    @Query("""
        SELECT episode_id, episode_title, episode_description, episode_podcast_id, 
            episode_publication_date_unix_seconds, episode_audio_url, episode_audio_size_in_bytes,
            episode_audio_duration_in_seconds, episode_explicit, episode_season,
            episode_number, episode_type, episode_image_url, episode_chapters_url
        FROM Episodes
        WHERE episode_podcast_id = :id
        ORDER BY episode_publication_date_unix_seconds DESC
    """)
    abstract fun getEpisodesByPodcastId(id: Long): PagingSource<Int, EpisodeEntity>

    @Query("""
        SELECT EXISTS(
            SELECT episode_id 
            FROM EPISODES 
            WHERE episode_publication_date_unix_seconds >= :publicationDateUnixMillis
            LIMIT 1
        )
    """)
    abstract suspend fun areEpisodesAlreadyLoadedForPublicationDate(
        publicationDateUnixMillis: Long,
    ): Boolean

}
