package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.util.EnumConverterFactory
import com.hoho.upbitapihelper.util.FileUtil
import com.hoho.upbitapihelper.util.TestUtil
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.*
import retrofit2.Retrofit
import retrofit2.create

@DisplayName("Unit Test - Api Manager Service")
internal class ApiManagerServiceTest {

    private val mockDataPath = "mock-data/api-manager"

    private lateinit var amService: ApiManagerService
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    @OptIn(ExperimentalSerializationApi::class)
    fun beforeEach() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val mockUrl = mockWebServer.url("/")

        amService = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .addConverterFactory(EnumConverterFactory())
            .baseUrl(mockUrl)
            .build()
            .create()
    }

    @AfterEach
    fun afterEach() {
        mockWebServer.shutdown()
    }

    @Test
    @DisplayName("공지사항 조회 - Success")
    fun getNoticesSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getNotices-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val page = 1
        val perPage = 3

        // When
        val callSync = amService.getNotices(page, perPage)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("업비트 소식 조회 - Success")
    fun getNewsSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getNews-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val page = 1
        val perPage = 3

        // When
        val callSync = amService.getNews(page, perPage)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }
}
