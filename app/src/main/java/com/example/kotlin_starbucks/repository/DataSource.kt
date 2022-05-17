package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.*
import kotlinx.coroutines.flow.Flow

interface DataSource {

    suspend fun loadEventImageContents(): Flow<EventImageContents?>

    suspend fun loadHomeContents(): Flow<HomeProducts?>

    suspend fun loadStarbucksContents(productCd: Long?): Flow<YourRecommendProductsTitle?>

    suspend fun loadStarbucksImages(productCd: Long?): Flow<YourRecommendProductsImage?>

    suspend fun loadHomeEvents(key: String): Flow<HomeEvents?>
}