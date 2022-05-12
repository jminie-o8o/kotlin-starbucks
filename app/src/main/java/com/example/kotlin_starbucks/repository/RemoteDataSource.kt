package com.example.kotlin_starbucks.repository

import android.util.Log
import com.example.kotlin_starbucks.model.*
import com.example.kotlin_starbucks.network.RetrofitObject
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor() : DataSource {
    override suspend fun loadEventImageContents(): EventImageContents? {
        val response = RetrofitObject.codeSquadService.loadEventImageContents()
        return response.getBodyOrNull()
    }

    override suspend fun loadHomeContents(): HomeProducts? {
        val response = RetrofitObject.codeSquadApi.loadHomeContents()
        return response.getBodyOrNull()
    }

    override suspend fun loadStarbucksContents(productCd: Long?): YourRecommendProductsTitle? {
        val response = RetrofitObject.starBucksInfo.loadStarbucksContents(productCd)
        return response.getBodyOrNull()
    }

    override suspend fun loadStarbucksImages(productCd: Long?): YourRecommendProductsImage? {
        val response = RetrofitObject.starBucksInfo.loadStarbucksContentsImage(productCd)
        return response.getBodyOrNull()
    }

    override suspend fun loadHomeEvents(key: String): HomeEvents? {
        val response = RetrofitObject.starBucksInfo.loadHomeEvents(key)
        return response.getBodyOrNull()
    }

    private fun <T> Response<T>.getBodyOrNull(): T? {
        return if (this.isSuccessful) this.body() else null
    }
}