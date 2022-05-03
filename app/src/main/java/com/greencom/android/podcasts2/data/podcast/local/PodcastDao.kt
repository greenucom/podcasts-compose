package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.*
import com.greencom.android.podcasts2.data.episode.local.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class PodcastDao {

    @Query("DELETE FROM PodcastTemp")
    protected abstract suspend fun clearTemp()

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcastEntity: PodcastEntity)

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcastEntities: List<PodcastEntity>)

    @Query("""
        INSERT INTO Podcast (podcast_id, podcast_title, podcast_description, podcast_author, 
            podcast_image_url, podcast_categories, podcast_is_subscribed)
        SELECT tmp.podcast_id, tmp.podcast_title, tmp.podcast_description, tmp.podcast_author, 
            tmp.podcast_image_url, tmp.podcast_categories, tmp.podcast_is_subscribed
        FROM PodcastTemp tmp
        LEFT JOIN Podcast ON tmp.podcast_id = Podcast.podcast_id 
        WHERE Podcast.podcast_id IS NULL
    """)
    protected abstract suspend fun mergeTemp()

    @Transaction
    open suspend fun insert(podcastEntity: PodcastEntity) {
        insertToTemp(podcastEntity)
        mergeTemp()
        update(podcastEntity)
        clearTemp()
    }

    @Transaction
    open suspend fun insert(podcastEntities: List<PodcastEntity>) {
        insertToTemp(podcastEntities)
        mergeTemp()
        update(podcastEntities)
        clearTemp()
    }

    @Update
    protected abstract suspend fun update(podcastEntity: PodcastEntity)

    @Update
    protected abstract suspend fun update(podcastEntities: List<PodcastEntity>)

    @Query("SELECT podcast_id FROM Podcast WHERE podcast_is_subscribed = 1")
    abstract suspend fun getPodcastSubscriptionIds(): List<Long>

    @Query("""
        SELECT 
            p.podcast_id, p.podcast_title, p.podcast_description, p.podcast_author, 
                p.podcast_image_url, p.podcast_categories, p.podcast_is_subscribed,
            e.episode_id, e.episode_title, e.episode_description, e.episode_date_unix, 
                e.episode_serial_number, e.episode_type, e.episode_explicit, 
                e.episode_audio_url, e.episode_audio_size_in_bytes, 
                e.episode_audio_duration_in_milliseconds, e.episode_chapters_url, 
                e.episode_image_url, e.episode_podcast_id
        FROM Podcast p
        LEFT JOIN Episode e ON p.podcast_id = e.episode_podcast_id
        WHERE p.podcast_id = :id
        ORDER BY e.episode_date_unix DESC
    """)
    protected abstract fun getPodcastWithEpisodesByIdFlowRaw(id: Long):
            Flow<Map<PodcastEntity?, List<EpisodeEntity>>>

    fun getPodcastWithEpisodesByIdFlow(id: Long): Flow<Map<PodcastEntity?, List<EpisodeEntity>>> =
        getPodcastWithEpisodesByIdFlowRaw(id).distinctUntilChanged()

}
