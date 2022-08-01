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

@DisplayName("Unit Test - Project Team Service")
internal class ProjectTeamServiceTest {

    private val mockDataPath = "mock-data/project-team"

    private lateinit var ptService: ProjectTeamService
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    @OptIn(ExperimentalSerializationApi::class)
    fun beforeEach() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val mockUrl = mockWebServer.url("/")

        ptService = Retrofit.Builder()
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
    @DisplayName("프로젝트 공시 조회 - Success")
    fun getDisclosuresSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getDisclosures-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val perPage = 3
        val offset: Int? = null
        val region = "kr"

        // When
        val call = ptService.getDisclosures(perPage, offset, region)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("프로젝트 공시 조회 - Failure")
    fun getDisclosuresFailureTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getDisclosures-failure.json")
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody(mockBody))

        val perPage = 3
        val offset: Int? = null
        val region = "kr"

        // When
        val call = ptService.getDisclosures(perPage, offset, region)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertFalse(response.isSuccessful)
    }
}
