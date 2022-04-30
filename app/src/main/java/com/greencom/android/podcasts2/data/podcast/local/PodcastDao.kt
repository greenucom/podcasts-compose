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
        INSERT INTO Podcast (id, title, description, author, image_url, categories, is_subscribed)
        SELECT tmp.id, tmp.title, tmp.description, tmp.author, tmp.image_url, tmp.categories, 
            tmp.is_subscribed
        FROM PodcastTemp tmp
        LEFT JOIN Podcast ON tmp.id = Podcast.id 
        WHERE Podcast.id IS NULL
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

    @Query("SELECT id FROM Podcast WHERE is_subscribed = 1")
    abstract suspend fun getSubscriptionIds(): List<Long>

    @Query("""
        SELECT 
            p.id, p.title, p.description, p.author, p.image_url, p.categories, p.is_subscribed,
            e.id, e.title, e.description, e.date_unix, e.serial_number_season, 
                e.serial_number_episode, e.type, e.explicit, e.audio_url, e.audio_size_in_bytes,
                e.audio_duration_in_milliseconds, e.chapters_url, e.image_url, e.podcast_id
        FROM Podcast p
        LEFT JOIN Episode e ON e.podcast_id = p.id
        WHERE p.id = :id
    """)
    protected abstract fun getPodcastWithEpisodesByIdFlowRaw(id: Long):
            Flow<Map<PodcastEntity, List<EpisodeEntity>>>

    fun getPodcastWithEpisodesByIdFlow(id: Long): Flow<Map<PodcastEntity, List<EpisodeEntity>>> =
        getPodcastWithEpisodesByIdFlowRaw(id).distinctUntilChanged()

}
