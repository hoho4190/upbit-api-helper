package com.hoho.upbitapihelper.util

import com.hoho.upbitapihelper.dto.ApiUrlInfo
import com.hoho.upbitapihelper.dto.ErrorResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response

internal object RetrofitUtil {

    private const val API_URL_INFO_RES_FILE_NAME = "api-url-info.json"
    val apiUrlInfo = parseApiUrlInfo()

    /**
     * Parse ApiUrlInfo
     */
    private fun parseApiUrlInfo(): ApiUrlInfo {
        val apiUrlInfo: ApiUrlInfo = Json.decodeFromString(FileUtil.readResource(API_URL_INFO_RES_FILE_NAME))
        val apiManagerUrl = apiUrlInfo.apiManager
        val projectTeamUrl = apiUrlInfo.projectTeam
        val apiUrl = apiUrlInfo.api

        if (!apiManagerUrl.endsWith("/")) {
            apiUrlInfo.apiManager += "/"
        }

        if (!projectTeamUrl.endsWith("/")) {
            apiUrlInfo.projectTeam += "/"
        }

        if (!apiUrl.endsWith("/")) {
            apiUrlInfo.api += "/"
        }

        return apiUrlInfo
    }

    /**
     * Get ErrorResponse of response.
     *
     * @param response
     */
    fun <T> getErrorResponse(response: Response<T>): ErrorResponse? {
        return if (response.errorBody() == null) {
            null
        } else {
            val result: ErrorResponse = try {
                Json.decodeFromString(response.errorBody()!!.string())
            } catch (e: Exception) {
                ErrorResponse(
                    ErrorResponse.Error(
                        "Error parsing failed",
                        "unknown error"
                    )
                )
            }

            result
        }
    }
}
