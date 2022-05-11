package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.model.HomeProducts

interface DataSource {

    suspend fun loadEventImageContents(): EventImageContents?

    suspend fun loadHomeContents(): HomeProducts?
}