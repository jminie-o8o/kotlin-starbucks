package com.example.kotlin_starbucks.network

import com.example.kotlin_starbucks.model.*
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPI {

    @GET("jk/boostcamp/starbuckst-loading.json")
    suspend fun loadEventImageContents(): Response<EventImageContents>

    @GET("starbuckst/")
    suspend fun loadHomeContents(): Response<HomeProducts>

    @FormUrlEncoded
    @POST("menu/productViewAjax.do")
    suspend fun loadStarbucksContents(
        @Field("product_cd") productCd: Long?
    ): Response<YourRecommendProductsTitle>

    @FormUrlEncoded
    @POST("menu/productFileAjax.do")
    suspend fun loadStarbucksContentsImage(
        @Field("PRODUCT_CD") productCd: Long?
    ): Response<YourRecommendProductsImage>

    @FormUrlEncoded
    @POST("whats_new/getIngList.do")
    suspend fun loadHomeEvents(
        @Field("MENU_CD") key: String
    ): Response<HomeEvents>
}