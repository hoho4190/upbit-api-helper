package com.hoho.upbitapihelper.util

import com.hoho.upbitapihelper.dto.ApiUrlInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
}