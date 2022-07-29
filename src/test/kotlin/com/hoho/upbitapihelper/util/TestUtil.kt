package com.hoho.upbitapihelper.util

import com.hoho.upbitapihelper.dto.OpenApiKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
     * Convert any to pretty String.
     *
     * @param any
     */
    inline fun <reified T> convertPrettyString(any: T?): String? {
        return if (any == null) {
            null
        } else {
            json.encodeToString(any)
        }
    }
}
