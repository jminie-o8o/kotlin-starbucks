package com.example.kotlin_starbucks.model

data class FlowException(
    val throwable: Throwable,
    val errorMessage: String
)
