package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: DataSource) {

    suspend fun loadEventImageContents(): Flow<EventImageContents?> {
        return dataSource.loadEventImageContents()
    }

    suspend fun loadHomeContents(): Flow<HomeProducts?> {
        return dataSource.loadHomeContents()
    }

    suspend fun loadStarbucksContents(productCd: Long?): Flow<YourRecommendProductsTitle?> {
        return dataSource.loadStarbucksContents(productCd)
    }

    suspend fun loadStarbucksImages(productCd: Long?): Flow<YourRecommendProductsImage?> {
        return dataSource.loadStarbucksImages(productCd)
    }

    suspend fun loadHomeEvents(key: String): HomeEvents? {
        return dataSource.loadHomeEvents(key)
    }
}