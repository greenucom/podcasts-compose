package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.*

@Dao
abstract class PodcastDao {

    @Query("DELETE FROM podcasts_temp")
    protected abstract suspend fun clearTemp()

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcast: PodcastEntity)

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcasts: List<PodcastEntity>)

    @Query("""
        INSERT INTO podcasts (id, title, description, author, image, categories, is_subscribed)
        SELECT tmp.id, tmp.title, tmp.description, tmp.author, tmp.image, tmp.categories, 
            tmp.is_subscribed
        FROM podcasts_temp tmp
        LEFT JOIN podcasts ON tmp.id = podcasts.id 
        WHERE podcasts.id IS NULL
    """)
    protected abstract suspend fun mergeTemp()

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

    @Update
    protected abstract suspend fun update(podcast: PodcastEntity)

    @Update
    protected abstract suspend fun update(podcasts: List<PodcastEntity>)

    @Query("SELECT id FROM podcasts WHERE is_subscribed = 1")
    abstract suspend fun getSubscriptionIds(): List<Long>

}
