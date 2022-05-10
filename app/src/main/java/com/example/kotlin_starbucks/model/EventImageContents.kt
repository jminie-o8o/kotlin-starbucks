package com.example.kotlin_starbucks.model

import com.google.gson.annotations.SerializedName

data class EventImageContents(
    @SerializedName("title")
    val title: String?,
    @SerializedName("range")
    val range: String?,
    @SerializedName("target")
    val target: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("event-products")
    val eventProducts: String?
)
