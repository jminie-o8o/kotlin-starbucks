package com.example.kotlin_starbucks.network

import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.model.HomeProducts
import com.example.kotlin_starbucks.model.ProductCd
import com.example.kotlin_starbucks.model.YourRecommendProducts
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkAPI {

    @GET("jk/boostcamp/starbuckst-loading.json")
    suspend fun loadEventImageContents(): Response<EventImageContents>

    @GET("starbuckst/")
    suspend fun loadHomeContents(): Response<HomeProducts>

    @POST("productViewAjax.do")
    suspend fun loadStarbucksCont(
        @Body productCd: ProductCd
    ): Response<YourRecommendProducts>
}