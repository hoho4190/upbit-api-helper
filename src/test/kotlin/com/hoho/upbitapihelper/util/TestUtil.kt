package com.hoho.upbitapihelper.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object TestUtil {

    private val json: Json = Json { prettyPrint = true }

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
