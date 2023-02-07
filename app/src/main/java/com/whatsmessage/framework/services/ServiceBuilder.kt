package com.whatsmessage.framework.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    //service generator for request
    private const val URL_API = "API"

    private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(logger)

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit: Retrofit = builder.build()

    fun <T> build(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}