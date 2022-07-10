package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class PodcastDao {

    @Transaction
    open suspend fun insert(podcast: PodcastEntity) {
        insertToTemp(podcast)
        mergeTemp()
        update(podcast)
        clearTemp()
    }

    @Transaction
    open suspend fun insert(podcasts: List<PodcastEntity>) {
        insertToTemp(podcasts)
        mergeTemp()
        update(podcasts)
        clearTemp()
    }

    @Query("SELECT podcast_id FROM Podcasts WHERE podcast_is_user_subscribed = 1")
    abstract suspend fun getUserSubscriptionsIds(): List<Long>

    fun getPodcastById(id: Long): Flow<PodcastEntity> =
        getPodcastByIdImpl(id).distinctUntilChanged()

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcast: PodcastEntity)

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcasts: List<PodcastEntity>)

    @Query("""
        INSERT INTO 
            Podcasts (podcast_id, podcast_title, podcast_description, podcast_author, 
                podcast_image_url, podcast_categories, podcast_is_user_subscribed)
        SELECT 
            Tmp.podcast_id, Tmp.podcast_title, Tmp.podcast_description, Tmp.podcast_author,
                Tmp.podcast_image_url, Tmp.podcast_categories, Tmp.podcast_is_user_subscribed
        FROM PodcastsTemp Tmp
        LEFT JOIN Podcasts ON Tmp.podcast_id = Podcasts.podcast_id
        WHERE Podcasts.podcast_id IS NULL
    """)
    protected abstract suspend fun mergeTemp()

    @Update
    protected abstract suspend fun update(podcast: PodcastEntity)

    @Update
    protected abstract suspend fun update(podcasts: List<PodcastEntity>)

    @Query("DELETE FROM PodcastsTemp")
    protected abstract suspend fun clearTemp()

    @Query("""
        SELECT podcast_id, podcast_title, podcast_description, podcast_author, podcast_image_url,
            podcast_categories, podcast_is_user_subscribed
        FROM Podcasts
        WHERE podcast_id = :id
    """)
    protected abstract fun getPodcastByIdImpl(id: Long): Flow<PodcastEntity>

}
