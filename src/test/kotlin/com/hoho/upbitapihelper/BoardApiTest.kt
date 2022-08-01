package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.ErrorResponse
import com.hoho.upbitapihelper.util.RetrofitUtil
import com.hoho.upbitapihelper.util.TestUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Disabled
@DisplayName("E2E Test - Board API")
internal class BoardApiTest {

    @Disabled
    @Test
    @DisplayName("공지사항 조회")
    fun getNoticeListTest() {
        // Given
        val page = 1
        val perPage = 20

        // When
        val call = BoardApi.getNoticeList(page, perPage)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("list size: ${result!!.data.list.size}")
            println("fixedNotices size: ${result.data.fixedNotices.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("업비트 소식 조회")
    fun getNewsListTest() {
        // Given
        val page = 1
        val perPage = 20

        // When
        val call = BoardApi.getNewsList(page, perPage)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("list size: ${result!!.data.list.size}")
            println("fixedNotices size: ${result.data.fixedNotices.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("프로젝트 공시 목록 조회")
    fun getDisclosureListTest() {
        // Given
        val perPage = 5
        val offset: Int? = null
        val region = "kr"

        // When
        val call = BoardApi.getDisclosureList(perPage, offset, region)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("posts size: ${result!!.data.posts.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }
}
