package com.example.kotlin_starbucks.model

import com.google.gson.annotations.SerializedName

data class ProductCd(
    @SerializedName("product_cd")
    val productCd: String?
)
