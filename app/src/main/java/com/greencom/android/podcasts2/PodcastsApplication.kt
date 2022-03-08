package com.greencom.android.podcasts2

import android.app.Application
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PodcastsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ensureInitialization()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun ensureInitialization() {
        BottomNavBarItem.items
    }

}
