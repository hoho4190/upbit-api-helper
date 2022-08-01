package com.hoho.upbitapihelper.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.hoho.upbitapihelper.dto.ApiUrlInfo
import com.hoho.upbitapihelper.dto.OpenApiKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

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
     * Get AuthToken.
     *
     * @param apiKey
     * @return AuthToken
     */
    fun getAuthToken(
        apiKey: OpenApiKey
    ): String {
        val algorithm: Algorithm = Algorithm.HMAC256(apiKey.secretKey)
        val jwtToken = JWT.create()
            .withClaim("access_key", apiKey.accessKey)
            .withClaim("nonce", UUID.randomUUID().toString())
            .sign(algorithm)

        return "Bearer $jwtToken"
    }

    /**
     * AuthToken 생성
     *
     * @param apiKey
     * @param queryString key=value[&key=value]
     * @return AuthToken
     */
    fun getAuthToken(
        apiKey: OpenApiKey,
        queryString: String
    ): String {
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(queryString.toByteArray(charset("UTF-8")))

        val queryHash = String.format("%0128x", BigInteger(1, md.digest()))

        val algorithm: Algorithm = Algorithm.HMAC256(apiKey.secretKey)
        val jwtToken = JWT.create()
            .withClaim("access_key", apiKey.accessKey)
            .withClaim("nonce", UUID.randomUUID().toString())
            .withClaim("query_hash", queryHash)
            .withClaim("query_hash_alg", "SHA512")
            .sign(algorithm)

        return "Bearer $jwtToken"
    }

    /**
     * AuthToken 생성
     *
     * @param apiKey
     * @param queryElements
     * @return AuthToken
     */
    fun getAuthToken(
        apiKey: OpenApiKey,
        queryElements: ArrayList<String>
    ): String {
        val queryString = java.lang.String.join("&", *queryElements.toTypedArray())

        return getAuthToken(apiKey, queryString)
    }

    /**
     * AuthToken 생성
     *
     * @param apiKey
     * @param queryMap
     * @return AuthToken
     */
    fun getAuthToken(
        apiKey: OpenApiKey,
        queryMap: HashMap<String, String>
    ): String {
        val queryElements = ArrayList<String>()
        for ((key, value) in queryMap) {
            queryElements.add("$key=$value")
        }

        return getAuthToken(apiKey, queryElements)
    }
}
