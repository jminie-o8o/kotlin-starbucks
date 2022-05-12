package com.example.kotlin_starbucks.model


import com.google.gson.annotations.SerializedName

data class YourRecommendProducts(
    @SerializedName("file")
    val file: List<File>?
) {
    data class File(
        @SerializedName("all_CATE_CD")
        val allCATECD: String?,
        @SerializedName("allergy")
        val allergy: String?,
        @SerializedName("app_IMAGE")
        val appIMAGE: String?,
        @SerializedName("caffeine")
        val caffeine: String?,
        @SerializedName("caffeine_L")
        val caffeineL: String?,
        @SerializedName("card_MONTH")
        val cardMONTH: String?,
        @SerializedName("card_TABLE")
        val cardTABLE: String?,
        @SerializedName("card_YEAR")
        val cardYEAR: String?,
        @SerializedName("cate_CD")
        val cateCD: String?,
        @SerializedName("cate_NAME")
        val cateNAME: String?,
        @SerializedName("cate_TABLE")
        val cateTABLE: String?,
        @SerializedName("chabo")
        val chabo: String?,
        @SerializedName("chabo_L")
        val chaboL: String?,
        @SerializedName("cholesterol")
        val cholesterol: String?,
        @SerializedName("cholesterol_L")
        val cholesterolL: String?,
        @SerializedName("content")
        val content: String?,
        @SerializedName("del_YN")
        val delYN: String?,
        @SerializedName("egift_CARD_YN")
        val egiftCARDYN: String?,
        @SerializedName("egift_IMG_CATG_CODE")
        val egiftIMGCATGCODE: String?,
        @SerializedName("f_CATE_CD")
        val fCATECD: String?,
        @SerializedName("fat")
        val fat: String?,
        @SerializedName("fat_L")
        val fatL: String?,
        @SerializedName("file_MASTER")
        val fileMASTER: String?,
        @SerializedName("file_NAME")
        val fileNAME: String?,
        @SerializedName("file_PATH")
        val filePATH: String?,
        @SerializedName("file_TABLE")
        val fileTABLE: String?,
        @SerializedName("first_INDEX")
        val firstINDEX: Int?,
        @SerializedName("front_VIEW_YN")
        val frontVIEWYN: String?,
        @SerializedName("gift_VALUE")
        val giftVALUE: String?,
        @SerializedName("hot_YN")
        val hotYN: String?,
        @SerializedName("img_ORDER")
        val imgORDER: String?,
        @SerializedName("img_UPLOAD_PATH")
        val imgUPLOADPATH: String?,
        @SerializedName("info_TABLE")
        val infoTABLE: String?,
        @SerializedName("kcal")
        val kcal: String?,
        @SerializedName("kcal_L")
        val kcalL: String?,
        @SerializedName("last_INDEX")
        val lastINDEX: Int?,
        @SerializedName("mod_DT")
        val modDT: String?,
        @SerializedName("mod_USER")
        val modUSER: String?,
        @SerializedName("msr_SEQ")
        val msrSEQ: Int?,
        @SerializedName("msr_SEQ2")
        val msrSEQ2: String?,
        @SerializedName("new_EDATE")
        val newEDATE: String?,
        @SerializedName("new_SDATE")
        val newSDATE: String?,
        @SerializedName("newicon")
        val newicon: String?,
        @SerializedName("note_TYPE")
        val noteTYPE: String?,
        @SerializedName("nut_TABLE")
        val nutTABLE: String?,
        @SerializedName("page_INDEX")
        val pageINDEX: Int?,
        @SerializedName("page_SIZE")
        val pageSIZE: Int?,
        @SerializedName("page_UNIT")
        val pageUNIT: Int?,
        @SerializedName("pair_TABLE")
        val pairTABLE: String?,
        @SerializedName("pcate_CD")
        val pcateCD: String?,
        @SerializedName("premier")
        val premier: String?,
        @SerializedName("price")
        val price: String?,
        @SerializedName("pro_SEQ")
        val proSEQ: Int?,
        @SerializedName("product_CD")
        val productCD: String?,
        @SerializedName("product_ENGNM")
        val productENGNM: String?,
        @SerializedName("product_NM")
        val productNM: String?,
        @SerializedName("protein")
        val protein: String?,
        @SerializedName("protein_L")
        val proteinL: String?,
        @SerializedName("recomm")
        val recomm: String?,
        @SerializedName("recommend")
        val recommend: String?,
        @SerializedName("record_COUNT_PER_PAGE")
        val recordCOUNTPERPAGE: Int?,
        @SerializedName("reg_DT")
        val regDT: String?,
        @SerializedName("reg_USER")
        val regUSER: String?,
        @SerializedName("rnum")
        val rnum: Int?,
        @SerializedName("sat_FAT")
        val satFAT: String?,
        @SerializedName("sat_FAT_L")
        val satFATL: String?,
        @SerializedName("search_1_CATE")
        val search1CATE: Any?,
        @SerializedName("search_2_CATE")
        val search2CATE: Any?,
        @SerializedName("search_3_CATE")
        val search3CATE: Any?,
        @SerializedName("search_DATE_TYPE")
        val searchDATETYPE: Any?,
        @SerializedName("search_END_DATE")
        val searchENDDATE: Any?,
        @SerializedName("search_KEY")
        val searchKEY: String?,
        @SerializedName("search_SALE_TYPE")
        val searchSALETYPE: Any?,
        @SerializedName("search_START_DATE")
        val searchSTARTDATE: Any?,
        @SerializedName("search_VALUE")
        val searchVALUE: Any?,
        @SerializedName("search_VIEW_YN")
        val searchVIEWYN: Any?,
        @SerializedName("sell_CAT")
        val sellCAT: String?,
        @SerializedName("sodium")
        val sodium: String?,
        @SerializedName("sodium_L")
        val sodiumL: String?,
        @SerializedName("sold_OUT")
        val soldOUT: String?,
        @SerializedName("standard")
        val standard: String?,
        @SerializedName("sub_VIEW")
        val subVIEW: String?,
        @SerializedName("sugars")
        val sugars: String?,
        @SerializedName("sugars_L")
        val sugarsL: String?,
        @SerializedName("theme_SEARCH")
        val themeSEARCH: String?,
        @SerializedName("thumbnail")
        val thumbnail: String?,
        @SerializedName("total_CNT")
        val totalCNT: Int?,
        @SerializedName("trans_FAT")
        val transFAT: String?,
        @SerializedName("trans_FAT_L")
        val transFATL: String?,
        @SerializedName("unit")
        val unit: String?,
        @SerializedName("view_EDATE")
        val viewEDATE: String?,
        @SerializedName("view_SDATE")
        val viewSDATE: String?,
        @SerializedName("view_YN")
        val viewYN: String?,
        @SerializedName("web_CARD_BIRTH")
        val webCARDBIRTH: String?,
        @SerializedName("web_IMAGE_MOBVIEW")
        val webIMAGEMOBVIEW: String?,
        @SerializedName("web_IMAGE_TABVIEW")
        val webIMAGETABVIEW: String?,
        @SerializedName("web_IMAGE_WEBVIEW")
        val webIMAGEWEBVIEW: String?,
        @SerializedName("web_NEW")
        val webNEW: String?,
        @SerializedName("youtube_CODE")
        val youtubeCODE: String?
    )
}