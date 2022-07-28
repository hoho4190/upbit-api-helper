package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.board.Board
import com.hoho.upbitapihelper.dto.board.DisclosureData
import com.hoho.upbitapihelper.util.EnumConverterFactory
import com.hoho.upbitapihelper.util.RetrofitUtil
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 업비트 게시판 API
 *
 * 프로젝트 공시
 */
internal interface ProjectTeamService {

    companion object {
        @JvmStatic
        @OptIn(ExperimentalSerializationApi::class)
        val instance: ProjectTeamService = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .addConverterFactory(EnumConverterFactory())
            .baseUrl(RetrofitUtil.apiUrlInfo.projectTeam)
            .build()
            .create()
    }

    /**
     * 프로젝트 공시 조회
     *
     * offset 아래로(미만) perPage 만큼 조회
     *
     * @param perPage
     * @param offset 조회의 기준이 되는 공시 아이디. 기본 값: null
     * @param region 기본 값: kr
     * @return
     */
    @GET("disclosure")
    fun getDisclosures(
        @Query("per_page") perPage: Int,
        @Query("offset") offset: Int? = null,
        @Query("region") region: String = "kr"
    ): Call<Board<DisclosureData>>
}
