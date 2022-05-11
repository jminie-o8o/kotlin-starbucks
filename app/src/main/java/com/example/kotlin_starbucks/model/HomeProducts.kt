package com.example.kotlin_starbucks.model

import com.google.gson.annotations.SerializedName

data class HomeProducts(
    @SerializedName("display-name")
    val displayName: String?,
    @SerializedName("your-recommend")
    val products: Products,
    @SerializedName("main-event")
    val mainEvent: MainEvent,
    @SerializedName("now-recommand")
    val nowRecommend: NowRecommend
) {
    data class Products(
        val products: List<String?>
    )
    data class MainEvent(
        @SerializedName("img_UPLOAD_PATH")
        val imgUploadPath: String?,
        @SerializedName("mob_THUM")
        val mobThumb: String?
    )
    data class NowRecommend(
        @SerializedName("products")
        val products: List<String?>
    )
}
