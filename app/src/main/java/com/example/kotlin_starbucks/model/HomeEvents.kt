package com.example.kotlin_starbucks.model


import com.google.gson.annotations.SerializedName

data class HomeEvents(
    @SerializedName("list")
    val list: List<HomeEventsContents>
) {
    data class HomeEventsContents(
        @SerializedName("app_BTN_IMG")
        val appBTNIMG: String?,
        @SerializedName("app_CONTN_BTN_LINK_URL")
        val appCONTNBTNLINKURL: Any?,
        @SerializedName("app_XPSR_YN")
        val appXPSRYN: Any?,
        @SerializedName("banner_TYPE")
        val bannerTYPE: Any?,
        @SerializedName("capacity")
        val capacity: String?,
        @SerializedName("content")
        val content: String?,
        @SerializedName("content_MOBILE")
        val contentMOBILE: String?,
        @SerializedName("content_TABLET")
        val contentTABLET: String?,
        @SerializedName("contn_CNTNT_XPSR_DVSN_CODE")
        val contnCNTNTXPSRDVSNCODE: Any?,
        @SerializedName("contn_CTN_CNTNT")
        val contnCTNCNTNT: Any?,
        @SerializedName("del_YN")
        val delYN: String?,
        @SerializedName("end_DT")
        val endDT: String?,
        @SerializedName("end_HOUR")
        val endHOUR: String?,
        @SerializedName("end_MINUTE")
        val endMINUTE: String?,
        @SerializedName("etc_MEMO")
        val etcMEMO: String?,
        @SerializedName("event_PAGE_NAME")
        val eventPAGENAME: String?,
        @SerializedName("file_CD")
        val fileCD: String?,
        @SerializedName("file_DEL_ARRAY")
        val fileDELARRAY: Any?,
        @SerializedName("file_NM")
        val fileNM: String?,
        @SerializedName("file_SEQ")
        val fileSEQ: Int?,
        @SerializedName("first_INDEX")
        val firstINDEX: Int?,
        @SerializedName("flash_H")
        val flashH: String?,
        @SerializedName("flash_NM")
        val flashNM: String?,
        @SerializedName("flash_W")
        val flashW: String?,
        @SerializedName("hit")
        val hit: String?,
        @SerializedName("img_NM")
        val imgNM: String?,
        @SerializedName("img_UPLOAD_PATH")
        val imgUPLOADPATH: String?,
        @SerializedName("last_INDEX")
        val lastINDEX: Int?,
        @SerializedName("menu_CD")
        val menuCD: String?,
        @SerializedName("menu_CD_ARR")
        val menuCDARR: Any?,
        @SerializedName("mob_IMG")
        val mobIMG: String?,
        @SerializedName("mob_IMG_SEQ")
        val mobIMGSEQ: Int?,
        @SerializedName("mob_THUM")
        val mobTHUM: String?,
        @SerializedName("mob_THUM_SEQ")
        val mobTHUMSEQ: Int?,
        @SerializedName("mod_DT")
        val modDT: String?,
        @SerializedName("mod_USER")
        val modUSER: String?,
        @SerializedName("online_YN")
        val onlineYN: String?,
        @SerializedName("page")
        val page: Int?,
        @SerializedName("page_INDEX")
        val pageINDEX: Int?,
        @SerializedName("page_SIZE")
        val pageSIZE: Int?,
        @SerializedName("page_UNIT")
        val pageUNIT: Int?,
        @SerializedName("pagesize")
        val pagesize: Int?,
        @SerializedName("pro_CON_SEQ")
        val proCONSEQ: Int?,
        @SerializedName("pro_SEQ")
        val proSEQ: Int?,
        @SerializedName("product_CD")
        val productCD: String?,
        @SerializedName("record")
        val record: Int?,
        @SerializedName("record_COUNT_PER_PAGE")
        val recordCOUNTPERPAGE: Int?,
        @SerializedName("reg_DT")
        val regDT: String?,
        @SerializedName("reg_USER")
        val regUSER: String?,
        @SerializedName("rnum")
        val rnum: Int?,
        @SerializedName("sbtitle_NAME")
        val sbtitleNAME: String?,
        @SerializedName("search_DATE_TYPE")
        val searchDATETYPE: String?,
        @SerializedName("search_END_DATE")
        val searchENDDATE: String?,
        @SerializedName("search_END_YN")
        val searchENDYN: String?,
        @SerializedName("search_KEY")
        val searchKEY: String?,
        @SerializedName("search_MENU_CD")
        val searchMENUCD: String?,
        @SerializedName("search_START_DATE")
        val searchSTARTDATE: String?,
        @SerializedName("search_VALUE")
        val searchVALUE: String?,
        @SerializedName("search_VIEW_YN")
        val searchVIEWYN: String?,
        @SerializedName("seq")
        val seq: String?,
        @SerializedName("start_DT")
        val startDT: String?,
        @SerializedName("start_HOUR")
        val startHOUR: String?,
        @SerializedName("start_MINUTE")
        val startMINUTE: String?,
        @SerializedName("stat")
        val stat: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("store_CD")
        val storeCD: String?,
        @SerializedName("store_CNT")
        val storeCNT: Int?,
        @SerializedName("store_CODE")
        val storeCODE: Any?,
        @SerializedName("store_GUBUN")
        val storeGUBUN: String?,
        @SerializedName("store_MEMO")
        val storeMEMO: String?,
        @SerializedName("store_NM")
        val storeNM: String?,
        @SerializedName("str_SEQ")
        val strSEQ: Int?,
        @SerializedName("sub_VIEW")
        val subVIEW: String?,
        @SerializedName("tab_IMG")
        val tabIMG: String?,
        @SerializedName("tab_IMG_SEQ")
        val tabIMGSEQ: Int?,
        @SerializedName("tab_THUM")
        val tabTHUM: String?,
        @SerializedName("tab_THUM_SEQ")
        val tabTHUMSEQ: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("top_DATE")
        val topDATE: String?,
        @SerializedName("top_YN")
        val topYN: String?,
        @SerializedName("total_CNT")
        val totalCNT: Int?,
        @SerializedName("view_DATE")
        val viewDATE: String?,
        @SerializedName("view_EDT1")
        val viewEDT1: String?,
        @SerializedName("view_EDT2")
        val viewEDT2: String?,
        @SerializedName("view_ORDER")
        val viewORDER: String?,
        @SerializedName("view_SDT1")
        val viewSDT1: String?,
        @SerializedName("view_SDT2")
        val viewSDT2: String?,
        @SerializedName("view_TYPE")
        val viewTYPE: String?,
        @SerializedName("view_YN")
        val viewYN: String?,
        @SerializedName("web_BTN_IMG")
        val webBTNIMG: String?,
        @SerializedName("web_CONTN_BTN_LINK_URL")
        val webCONTNBTNLINKURL: Any?,
        @SerializedName("web_CONTN_LINK_NWNDW_YN")
        val webCONTNLINKNWNDWYN: Any?,
        @SerializedName("web_IMG")
        val webIMG: String?,
        @SerializedName("web_IMG_SEQ")
        val webIMGSEQ: Int?,
        @SerializedName("web_THUM")
        val webTHUM: String?,
        @SerializedName("web_THUM_SEQ")
        val webTHUMSEQ: Int?,
        @SerializedName("web_XPSR_YN")
        val webXPSRYN: Any?,
        @SerializedName("webxpsrseqc")
        val webxpsrseqc: String?
    )
}