package com.greencom.android.podcasts2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}
