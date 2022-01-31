package com.greencom.android.podcasts2.data

import com.greencom.android.podcasts2.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import javax.inject.Inject

class ApiAuthInterceptor @Inject constructor(): Interceptor {

    private val apiKey = BuildConfig.apiKey
    private val apiSecret = BuildConfig.apiSecret

    private val version = BuildConfig.VERSION_NAME.takeWhile { it.isDigit() || it == '.' }
    private var userAgent = createUserAgent(version)

    override fun intercept(chain: Interceptor.Chain): Response {
        val epochMillis = System.currentTimeMillis().toString()
        val authSha1Hash = createAuthSha1Hash(epochMillis)

        val request = chain.request().newBuilder()
            .addHeader("User-Agent", userAgent)
            .addHeader("X-Auth-Key", apiKey)
            .addHeader("X-Auth-Date", epochMillis)
            .addHeader("Authorization", authSha1Hash)
            .build()

        return chain.proceed(request)
    }

    private fun createUserAgent(version: String): String {
        return "$USER_AGENT_NAME/$version ($USER_AGENT_LANGUAGE; $USER_AGENT_PLATFORM)"
    }

    private fun createAuthSha1Hash(epochMillis: String): String {
        val input = apiKey + apiSecret + epochMillis
        val bytes = MessageDigest
            .getInstance(SHA_1)
            .digest(input.toByteArray())
        return bytes.toHex()
    }

    private fun ByteArray.toHex(): String {
        val hexCharArray = HEX_CHARS.toCharArray()
        val result = StringBuilder(this.size * 2)
        this.forEach { b ->
            val i = b.toInt()
            result.append(hexCharArray[i shr 4 and 0xF])
            result.append(hexCharArray[i and 0xF])
        }
        return result.toString()
    }

    companion object {
        private const val USER_AGENT_NAME = "Podcasts Compose"
        private const val USER_AGENT_LANGUAGE = "Language=Kotlin"
        private const val USER_AGENT_PLATFORM = "Platform=Android"

        private const val SHA_1 = "SHA-1"
        private const val HEX_CHARS = "0123456789ABCDEF"
    }

}
