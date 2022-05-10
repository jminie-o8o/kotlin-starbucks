package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.EventImageContents
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: DataSource) {
    suspend fun loadEventImageContents(): EventImageContents? {
        return dataSource.loadEventImageContents()
    }
}