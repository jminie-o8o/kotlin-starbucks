package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.network.RetrofitObject
import javax.inject.Inject

class RemoteDataSource @Inject constructor() : DataSource {
    override suspend fun loadEventImageContents(): EventImageContents? {
        val response = RetrofitObject.codeSquadService.loadEventImageContents()
        return if (response.isSuccessful) response.body() else null
    }
}