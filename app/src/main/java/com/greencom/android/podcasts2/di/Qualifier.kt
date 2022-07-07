package com.greencom.android.podcasts2.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(@Suppress("unused") val dispatcher: PodcastsDispatcher)

enum class PodcastsDispatcher { MAIN, DEFAULT, IO }
