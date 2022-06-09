package com.greencom.android.podcasts2.utils

import com.greencom.android.podcasts2.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
    if (!BuildConfig.DEBUG) return this
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    return this.addInterceptor(loggingInterceptor)
}
