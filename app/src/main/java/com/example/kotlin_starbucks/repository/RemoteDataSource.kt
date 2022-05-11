package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.model.HomeProducts
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

    private fun <T> Response<T>.getBodyOrNull(): T? {
        return if (this.isSuccessful) this.body() else null
    }


}