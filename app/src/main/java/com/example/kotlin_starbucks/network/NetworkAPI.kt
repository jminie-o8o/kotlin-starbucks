package com.example.kotlin_starbucks.network

import com.example.kotlin_starbucks.model.EventImageContents
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPI {

    @GET("jk/boostcamp/starbuckst-loading.json")
    suspend fun loadEventImageContents(): Response<EventImageContents>
}