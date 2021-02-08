package com.goget.logic.dataaccess.networking

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class EndpointProvider<ENDPOINT> {
    private val BASE_URL = "https://gorest.co.in/public-api/"

    protected val endpoint: ENDPOINT

    constructor(httpClientHelper: HttpClientHelper, javaClass: Class<ENDPOINT>) {
        val okHttpClient = httpClientHelper.getClient()

        val gsonBuilder = GsonBuilder()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        endpoint = retrofit.create<ENDPOINT>(javaClass)
    }
}