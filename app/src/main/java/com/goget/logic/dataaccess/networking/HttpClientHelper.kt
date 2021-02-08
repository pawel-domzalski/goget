package com.goget.logic.dataaccess.networking

import android.content.Context
import com.goget.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File


class HttpClientHelper(context: Context) {

    private val CACHE_SIZE = 15 * 1024 * 1024
    private val CACHE_FILE_NAME = "network_cache"

    private var okHttpClient: OkHttpClient

    private val  headersForAllRequests = HashMap<String, String>()

    init {
        headersForAllRequests["Authorization"] = "Bearer 4e425a4d78a2816841a105941d14a7cdfc20886cd1dc6f30eac3c9e0bd9e4c68"
    }

    init {
        val cacheFile = File(context.cacheDir, CACHE_FILE_NAME)
        val cache = Cache(cacheFile, CACHE_SIZE.toLong())

        val builder = OkHttpClient.Builder().cache(cache)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            builder.addInterceptor(logging)
        }

        builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val requestBuilder = chain.request().newBuilder()

            for (key in headersForAllRequests.keys) {
                requestBuilder.addHeader(key, headersForAllRequests[key] ?: "")
            }

            val request: Request = requestBuilder.build()
            val response = chain.proceed(request)

            response
        })

        okHttpClient = builder.build()
    }

    fun getClient(): OkHttpClient {
        return okHttpClient
    }

}