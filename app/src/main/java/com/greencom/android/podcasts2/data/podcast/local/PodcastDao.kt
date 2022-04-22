package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.*

@Dao
abstract class PodcastDao {

    @Query("DELETE FROM podcasts_temp")
    protected abstract suspend fun clearTemp()

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcastEntity: PodcastEntity)

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcastEntities: List<PodcastEntity>)

    @Query("""
        INSERT INTO podcasts (id, title, description, author, image_url, categories, is_subscribed)
        SELECT tmp.id, tmp.title, tmp.description, tmp.author, tmp.image_url, tmp.categories, 
            tmp.is_subscribed
        FROM podcasts_temp tmp
        LEFT JOIN podcasts ON tmp.id = podcasts.id 
        WHERE podcasts.id IS NULL
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

    @Query("SELECT id FROM podcasts WHERE is_subscribed = 1")
    abstract suspend fun getSubscriptionIds(): List<Long>

}
