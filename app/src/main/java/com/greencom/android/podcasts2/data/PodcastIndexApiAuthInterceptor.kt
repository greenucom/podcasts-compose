package com.greencom.android.podcasts2.data

import com.greencom.android.podcasts2.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import javax.inject.Inject

class PodcastIndexApiAuthInterceptor @Inject constructor(): Interceptor {

    private val key = BuildConfig.PODCAST_INDEX_API_KEY
    private val secretKey = BuildConfig.PODCAST_INDEX_API_SECRET_KEY

    private val versionName = BuildConfig.VERSION_NAME.takeWhile { it.isDigit() || it == '.' }
    private var userAgent = createUserAgent(versionName)

    override fun intercept(chain: Interceptor.Chain): Response {
        val epochSeconds = (System.currentTimeMillis() / SECOND_IN_MILLIS).toString()
        val authSha1Checksum = calculateAuthSha1Checksum(epochSeconds)

        val request = chain.request().newBuilder()
            .addHeader("User-Agent", userAgent)
            .addHeader("X-Auth-Key", key)
            .addHeader("X-Auth-Date", epochSeconds)
            .addHeader("Authorization", authSha1Checksum)
            .build()

        return chain.proceed(request)
    }

    private fun createUserAgent(versionName: String): String {
        return "$USER_AGENT_NAME/$versionName ($USER_AGENT_LANGUAGE; $USER_AGENT_PLATFORM)"
    }

    private fun calculateAuthSha1Checksum(epochSeconds: String): String {
        val input = key + secretKey + epochSeconds
        val bytes = MessageDigest
            .getInstance(SHA_1)
            .digest(input.toByteArray())

        return bytes.joinToString("") { HEX_PATTERN.format(it) }
    }

    companion object {
        private const val USER_AGENT_NAME = "Podcasts Compose"
        private const val USER_AGENT_LANGUAGE = "Language=Kotlin"
        private const val USER_AGENT_PLATFORM = "Platform=Android"

        private const val SECOND_IN_MILLIS = 1000
        private const val SHA_1 = "SHA-1"
        private const val HEX_PATTERN = "%02x"
    }

}
