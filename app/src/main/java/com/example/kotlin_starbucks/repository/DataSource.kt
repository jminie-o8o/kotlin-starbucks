package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.model.HomeProducts
import com.example.kotlin_starbucks.model.ProductCd
import com.example.kotlin_starbucks.model.YourRecommendProducts

interface DataSource {

    suspend fun loadEventImageContents(): EventImageContents?

    suspend fun loadHomeContents(): HomeProducts?

    suspend fun loadStarbucksContents(productCd: Long?): YourRecommendProducts?
}