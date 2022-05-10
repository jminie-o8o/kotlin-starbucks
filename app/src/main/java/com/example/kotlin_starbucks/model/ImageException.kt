package com.example.kotlin_starbucks.model

data class ImageException(
    val throwable: Throwable,
    val errorMessage: String
)
