package com.greencom.android.podcasts2.data.podcasts.local

import androidx.room.*

@Dao
abstract class PodcastDao {

    @Transaction
    open suspend fun insert(podcasts: List<PodcastEntity>) {
        insertToTemp(podcasts)
        mergeTemp()
        update(podcasts)
        clearTemp()
    }

    @Insert(entity = PodcastEntityTemp::class, onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertToTemp(podcasts: List<PodcastEntity>)

    @Query("""
        INSERT INTO podcasts (id, title, description, author, image, is_subscribed)
        SELECT tmp.id, tmp.title, tmp.description, tmp.author, tmp.image, tmp.is_subscribed
        FROM podcasts_temp tmp
        LEFT JOIN podcasts ON tmp.id = podcasts.id 
        WHERE podcasts.id IS NULL
    """)
    protected abstract suspend fun mergeTemp()

    @Update(entity = PodcastEntity::class)
    protected abstract suspend fun update(podcasts: List<PodcastEntity>)

    @Query("DELETE FROM podcasts_temp")
    protected abstract suspend fun clearTemp()

}
