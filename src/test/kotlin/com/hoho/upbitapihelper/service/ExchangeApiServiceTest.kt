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

@DisplayName("Unit Test - Api Service(Exchange)")
internal class ExchangeApiServiceTest {

    private val mockDataPath = "mock-data/api/exchange"
    private val token = "token"

    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    @OptIn(ExperimentalSerializationApi::class)
    fun beforeEach() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val mockUrl = mockWebServer.url("/")

        apiService = Retrofit.Builder()
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
    @DisplayName("자산 - 전체 계좌 조회 - Success")
    fun getAccountsSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getAccounts-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        // When
        val callSync = apiService.getAccounts(token)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("서비스 정보 - 입출금 현황 - Success")
    fun getWalletStatusSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getWalletStatus-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        // When
        val callSync = apiService.getWalletStatus(token)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("서비스 정보 - API 키 리스트 조회 - Success")
    fun getApiKeysSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getApiKeys-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        // When
        val callSync = apiService.getApiKeys(token)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }
}
