package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.board.Board
import com.hoho.upbitapihelper.dto.board.NewsData
import com.hoho.upbitapihelper.dto.board.NoticeData
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
 * 공지사항, 업비트 소식
 */
internal interface ApiManagerService {

    companion object {
        @JvmStatic
        @OptIn(ExperimentalSerializationApi::class)
        val instance: ApiManagerService = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .addConverterFactory(EnumConverterFactory())
            .baseUrl(RetrofitUtil.apiUrlInfo.apiManager)
            .build()
            .create()
    }

    /**
     * 공지사항 조회
     *
     * @param page
     * @param perPage 기본 값: 20
     * @param threadName
     */
    @GET("notices")
    fun getNotices(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("thread_name") threadName: String = "general"
    ): Call<Board<NoticeData>>

    /**
     * 업비트 소식 조회
     *
     * @param page
     * @param perPage 기본 값: 20
     * @param threadName
     */
    @GET("notices")
    fun getNews(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("thread_name") threadName: String = "press"
    ): Call<Board<NewsData>>
}
