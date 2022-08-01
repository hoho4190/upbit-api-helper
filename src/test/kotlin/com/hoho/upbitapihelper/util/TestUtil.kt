package com.hoho.upbitapihelper.util

import com.hoho.upbitapihelper.dto.ErrorResponse
import com.hoho.upbitapihelper.dto.OpenApiKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Response

internal object TestUtil {

    private val json: Json = Json { prettyPrint = true }

    /**
     * Open API key resource file name.
     */
    private const val OPEN_API_KEY_RES_FILE_NAME = "open-api-key.json"

    /**
     * Get OpenApiKey.
     */
    @JvmStatic
    fun getOpenApiKey(): OpenApiKey =
        json.decodeFromString(FileUtil.readResource(OPEN_API_KEY_RES_FILE_NAME))

    /**
     * Convert Response to pretty String.
     *
     * @param response
     */
    inline fun <reified T> convertResToPrettyStr(response: Response<T>): String {
        return if (response.isSuccessful) {
            json.encodeToString(response.body())
        } else {
            val errorResponse =
                json.decodeFromString<ErrorResponse>(response.errorBody()!!.use { it.string() })
            json.encodeToString(errorResponse)
        }
    }
}
