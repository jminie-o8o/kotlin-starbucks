package com.example.kotlin_starbucks.repository

import com.example.kotlin_starbucks.model.EventImageContents

interface DataSource {

    suspend fun loadEventImageContents(): EventImageContents?
}