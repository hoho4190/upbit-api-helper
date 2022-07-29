package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 정렬 방식
 */
@Serializable
enum class OrderBy {

    /**
     * 오름차순
     */
    @SerialName("asc")
    ASC,

    /**
     * 내림차순
     */
    @SerialName("desc")
    DESC
}