package com.hoho.upbitapihelper.dto.board

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 공지사항
 */
@Serializable
data class Notice(

    val id: Long,

    val title: String,

    @SerialName("view_count")
    val viewCount: Long,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)
