package com.example.kotlin_starbucks.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private const val event = "https://public.codesquad.kr/"


    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    private val codeSquadRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(event)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val starBucksRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(event)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val codeSquadService: NetworkAPI by lazy {
        codeSquadRetrofit.create(NetworkAPI::class.java)
    }
}