package com.hoho.upbitapihelper.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Open API key
 */
@Serializable
data class OpenApiKey(

    @SerialName("access_key")
    val accessKey: String,

    @SerialName("secret_key")
    val secretKey: String
)