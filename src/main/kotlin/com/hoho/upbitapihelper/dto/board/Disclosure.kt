package com.hoho.upbitapihelper.dto.board

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 프로젝트 공시
 */
@Serializable
data class Disclosure(

    val id: Long,

    val assets: String,

    @SerialName("start_date")
    val startDate: String,

    @SerialName("end_date")
    val endDate: String,

    val text: String,

    @SerialName("text_i10n")
    val textI10n: String,

    val url: String
)
