package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiKey(

    /**
     * access key
     */
    @SerialName("access_key")
    val accessKey: String,

    /**
     * 만료 날짜
     *
     * DateString
     */
    @SerialName("expire_at")
    val expireAt: String
)