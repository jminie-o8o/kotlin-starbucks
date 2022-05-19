package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.*
import com.example.kotlin_starbucks.network.RetrofitObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSource @Inject constructor() : DataSource {
    override suspend fun loadEventImageContents(): Flow<EventImageContents?> {
        val response = RetrofitObject.codeSquadService.loadEventImageContents()
        return flow {
            emit(response.getBodyOrException())
        }
    }

    override suspend fun loadHomeContents(): Flow<HomeProducts?> {
        val response = RetrofitObject.codeSquadApi.loadHomeContents()
        return flow {
            emit(response.getBodyOrException())
        }
    }

    override suspend fun loadStarbucksContents(productCd: Long?): Flow<YourRecommendProductsTitle?> {
        val response = RetrofitObject.starBucksInfo.loadStarbucksContents(productCd)
        return flow { emit(response.getBodyOrException()) }
    }

    override suspend fun loadStarbucksImages(productCd: Long?): Flow<YourRecommendProductsImage?> {
        val response = RetrofitObject.starBucksInfo.loadStarbucksContentsImage(productCd)
        return flow { emit(response.getBodyOrException()) }
    }

    override suspend fun loadHomeEvents(key: String): Flow<HomeEvents?> {
        val response = RetrofitObject.starBucksInfo.loadHomeEvents(key)
        return flow { emit(response.getBodyOrException()) }
    }

    private fun <T> Response<T>.getBodyOrException(): T? {
        return if (this.isSuccessful) this.body() else throw Exception()
    }
}