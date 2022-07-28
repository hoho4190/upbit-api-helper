package com.hoho.upbitapihelper.dto.board

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 공지사항 데이터
 */
@Serializable
data class NoticeData(

    @SerialName("total_count")
    val totalCount: Long,

    @SerialName("total_pages")
    val totalPages: Long,

    val list: List<Notice>,

    @SerialName("fixed_notices")
    val fixedNotices: List<Notice>
) : BoardData()
