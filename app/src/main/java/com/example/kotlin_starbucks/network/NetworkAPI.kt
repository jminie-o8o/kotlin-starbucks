package com.example.kotlin_starbucks.network

import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.model.HomeProducts
import com.example.kotlin_starbucks.model.ProductCd
import com.example.kotlin_starbucks.model.YourRecommendProducts
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPI {

    @GET("jk/boostcamp/starbuckst-loading.json")
    suspend fun loadEventImageContents(): Response<EventImageContents>

    @GET("starbuckst/")
    suspend fun loadHomeContents(): Response<HomeProducts>

    @FormUrlEncoded
    @POST("productViewAjax.do")
    suspend fun loadStarbucksContents(
        @Field("product_cd") productCd: Long?
    ): Response<YourRecommendProducts>
}