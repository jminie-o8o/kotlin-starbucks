package com.example.kotlin_starbucks.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private const val event = "https://public.codesquad.kr/"
    private const val home = "https://api.codesquad.kr/"
    private const val starbucksInfo = "https://www.starbucks.co.kr/menu/"
    const val baseImageUrl = "https://image.istarbucks.co.kr"

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

    private val codeSquadStarbucksApi: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(home)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val starBucksRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(starbucksInfo)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val codeSquadService: NetworkAPI by lazy {
        codeSquadRetrofit.create(NetworkAPI::class.java)
    }

    val codeSquadApi: NetworkAPI by lazy {
        codeSquadStarbucksApi.create(NetworkAPI::class.java)
    }

    val starBucksInfo: NetworkAPI by lazy {
        starBucksRetrofit.create(NetworkAPI::class.java)
    }
}