package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.quotation.CandleUnit
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

@DisplayName("Unit Test - Api Service")
internal class ApiServiceTest {

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
    @DisplayName("시세 종목 조회 - 마켓 코드 조회 - Success")
    fun getMarketAllSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getMarketAll-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val isDetails = true

        // When
        val callSync = apiService.getMarketAll(isDetails)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 캔들 조회 - 분(Minute) 캔들 - Success")
    fun getCandlesMinutesSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getCandlesMinutes-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val unit: CandleUnit = CandleUnit.UNIT_60
        val market = "KRW-BTC"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val callSync = apiService.getCandlesMinutes(unit, market, to, count)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 캔들 조회 - 일(Day) 캔들 - Success")
    fun getCandlesDaysSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getCandlesDays-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3
        val convertingPriceUnit: String? = "KRW"

        // When
        val callSync = apiService.getCandlesDays(market, to, count, convertingPriceUnit)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 캔들 조회 - 주(Week) 캔들 - Success")
    fun getCandlesWeeksSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getCandlesWeeks-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val callSync = apiService.getCandlesWeeks(market, to, count)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 캔들 조회 - 월(Month) - Success")
    fun getCandlesMonthsSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getCandlesMonths-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val callSync = apiService.getCandlesMonths(market, to, count)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 체결 조회 - 최근 체결 내역 - Success")
    fun getTradesTicksSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getTradesTicks-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "KRW-BTC"
        val to: String? = "001400"
        val count: Int? = 3
        val cursor: String? = null
        val daysAgo: Int? = 2

        // When
        val callSync = apiService.getTradesTicks(market, to, count, cursor, daysAgo)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 Ticker 조회 - 현재가 정보 - Success")
    fun getTickerSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getTicker-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val markets = "KRW-BTC, KRW-ETH"

        // When
        val callSync = apiService.getTicker(markets)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }

    @Test
    @DisplayName("시세 호가 정보(Orderbook) 조회 - 호가 정보 조회 - Success")
    fun getOrderbookSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("mock-data/api/quotation/getOrderbook-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val markets: List<String> = listOf(
            "KRW-BTC",
            "KRW-ETH"
        )

        // When
        val callSync = apiService.getOrderbook(markets)
        val response = callSync.execute()

        // Then
        Assertions.assertTrue(response.isSuccessful)
        println(TestUtil.convertPrettyString(response.body()))
    }
}
