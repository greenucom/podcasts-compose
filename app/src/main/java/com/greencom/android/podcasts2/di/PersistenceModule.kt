package com.greencom.android.podcasts2.di

import android.content.Context
import androidx.room.Room
import com.greencom.android.podcasts2.data.PodcastsDatabase
import com.greencom.android.podcasts2.data.category.local.CategoryDtoListTypeConverter
import com.greencom.android.podcasts2.data.podcast.local.PodcastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private const val PODCASTS_DATABASE_NAME = "podcasts_database"

    @Provides
    fun providePodcastDao(database: PodcastsDatabase): PodcastDao = database.podcastDao()

    @Provides
    @Singleton
    fun providePodcastsDatabase(
        @ApplicationContext context: Context,
        json: Json,
    ): PodcastsDatabase {
        return Room.databaseBuilder(context, PodcastsDatabase::class.java, PODCASTS_DATABASE_NAME)
            .addTypeConverter(CategoryDtoListTypeConverter(json))
            .fallbackToDestructiveMigration()
            .build()
    }

}
