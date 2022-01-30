package com.greencom.android.podcasts2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.greencom.android.podcasts2.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private const val DATABASE_NAME = "podcasts_compose_database"

//    @Provides
//    @Singleton
//    fun providePodcastsDatabase(@ApplicationContext context: Context): PodcastsDatabase {
//        return Room.databaseBuilder(context, PodcastsDatabase::class.java, DATABASE_NAME)
//            .fallbackToDestructiveMigration()
//            .build()
//    }

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

}
