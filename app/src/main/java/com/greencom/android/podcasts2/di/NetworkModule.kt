package com.greencom.android.podcasts2.di

import com.greencom.android.podcasts2.data.ApiAuthInterceptor
import com.greencom.android.podcasts2.data.podcast.remote.PodcastService
import com.greencom.android.podcasts2.utils.addDebugLogger
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.podcastindex.org/api/1.0/"

    @Provides
    @Singleton
    fun providePodcastService(retrofit: Retrofit): PodcastService {
        return retrofit.create(PodcastService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json, httpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        @Suppress("EXPERIMENTAL_API_USAGE")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(apiAuthInterceptor: ApiAuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiAuthInterceptor)
            .addDebugLogger()
            .build()
    }

}
