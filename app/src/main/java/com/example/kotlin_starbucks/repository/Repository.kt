package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.*
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: DataSource) {
    suspend fun loadEventImageContents(): EventImageContents? {
        return dataSource.loadEventImageContents()
    }

    suspend fun loadHomeContents(): HomeProducts? {
        return dataSource.loadHomeContents()
    }

    suspend fun loadStarbucksContents(productCd: Long?): YourRecommendProductsTitle? {
        return dataSource.loadStarbucksContents(productCd)
    }

    suspend fun loadStarbucksImages(productCd: Long?): YourRecommendProductsImage? {
        return dataSource.loadStarbucksImages(productCd)
    }
}