package com.hoho.upbitapihelper.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response

/**
 * API 에러 응답
 */
@Serializable
data class ErrorResponse(
    val error: Error
) {
    companion object {

        @JvmStatic
        fun <T> from(response: Response<T>): ErrorResponse? {
            return if (response.errorBody() == null) {
                null
            } else {
                val result: ErrorResponse = try {
                    Json.decodeFromString(response.errorBody()!!.use { it.string() })
                } catch (e: Exception) {
                    ErrorResponse(
                        Error(
                            "Error parsing failed",
                            "unknown error"
                        )
                    )
                }

                result
            }
        }
    }

    @Serializable
    data class Error(
        val name: String,
        val message: String
    )
}