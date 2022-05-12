package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.*

interface DataSource {

    suspend fun loadEventImageContents(): EventImageContents?

    suspend fun loadHomeContents(): HomeProducts?

    suspend fun loadStarbucksContents(productCd: Long?): YourRecommendProductsTitle?

    suspend fun loadStarbucksImages(productCd: Long?): YourRecommendProductsImage?

    suspend fun loadHomeEvents(key: String): HomeEvents?
}