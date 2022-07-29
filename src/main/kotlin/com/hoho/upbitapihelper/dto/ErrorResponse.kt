package com.hoho.upbitapihelper.dto

import kotlinx.serialization.Serializable

/**
 * API 에러 응답
 */
@Serializable
data class ErrorResponse(
    val error: Error
) {

    @Serializable
    data class Error(
        val name: String,
        val message: String
    )
}