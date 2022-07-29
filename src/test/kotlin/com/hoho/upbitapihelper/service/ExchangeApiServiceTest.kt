package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.exchange.OrderBy
import com.hoho.upbitapihelper.dto.exchange.OrderState
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
    @DisplayName("주문 - 주문 가능 정보 - Success")
    fun getOrdersChanceSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getOrdersChance-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "KRW-BTC"

        // When
        val callSync = apiService.getOrdersChance(token, market)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("주문 - 개별 주문 조회 - Success")
    fun getOrderSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getOrder-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val uuid = "uuid"

        // When
        val callSync = apiService.getOrder(token, uuid, null)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("주문 - 주문 리스트 조회 - Success")
    fun getOrdersSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getOrders-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        var market: String? = null
        var uuids: List<String>? = null
        var state: OrderState? = null
        var states: List<OrderState>? = null
        var page: Int? = null
        var limit: Int? = null
        var orderBy: OrderBy? = null

        market = "KRW-BTC"
        states = listOf(
            OrderState.CANCEL,
            OrderState.DONE
        )
        page = 1
        limit = 100
        orderBy = OrderBy.ASC

        // When
        val callSync = apiService.getOrders(
            token,
            market, uuids, null, state, states, page, limit, orderBy
        )
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("주문 - 주문 취소 접수 - Success")
    fun deleteOrderSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/deleteOrder-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val uuid = "uuid"

        // When
        val callSync = apiService.deleteOrder(token, uuid, null)
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
