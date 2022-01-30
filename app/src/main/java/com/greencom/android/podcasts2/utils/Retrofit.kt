package com.greencom.android.podcasts2.utils

import com.greencom.android.podcasts2.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun OkHttpClient.Builder.addDebugLogger(): OkHttpClient.Builder {
    if (!BuildConfig.DEBUG) return this
    return this.addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    )
}
