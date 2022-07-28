package com.hoho.upbitapihelper

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
        val callSync = BoardApi.getNoticeList(page, perPage)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
//        println("url: ${response.raw().request().url()}")
    }

    @Disabled
    @Test
    @DisplayName("업비트 소식 조회")
    fun getNewsListTest() {
        // Given
        val page = 1
        val perPage = 20

        // When
        val callSync = BoardApi.getNewsList(page, perPage)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
//        println("url: ${response.raw().request().url()}")
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
        val callSync = BoardApi.getDisclosureList(perPage, offset, region)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
//        println("url: ${response.raw().request().url()}")
    }
}