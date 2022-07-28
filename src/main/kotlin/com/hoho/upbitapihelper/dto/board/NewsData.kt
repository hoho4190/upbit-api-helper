package com.hoho.upbitapihelper.dto.board

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 업비트 소식 데이터
 */
@Serializable
data class NewsData(

    @SerialName("total_count")
    val totalCount: Long,

    @SerialName("total_pages")
    val totalPages: Long,

    val list: List<News>,

    @SerialName("fixed_notices")
    val fixedNotices: List<News>
) : BoardData()
