package com.greencom.android.podcasts2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.*

@HiltAndroidApp
class PodcastsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, GLOBAL_TAG, message, t)
                }

                override fun createStackElementTag(element: StackTraceElement): String {
                    return String.format(
                        locale = Locale.US,
                        format = "%s/%s",
                        super.createStackElementTag(element),
                        element.methodName,
                    )
                }
            })
        }
    }

    companion object {
        private const val GLOBAL_TAG = "PODCASTS_TAG"
    }

}
