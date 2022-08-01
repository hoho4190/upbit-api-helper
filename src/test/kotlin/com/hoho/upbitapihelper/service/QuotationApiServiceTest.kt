package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.quotation.CandleUnit
import com.hoho.upbitapihelper.dto.quotation.OrderBook
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
import retrofit2.*

@DisplayName("Unit Test - Api Service(Quotation)")
internal class QuotationApiServiceTest {

    private val mockDataPath = "mock-data/api/quotation"

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
        val mockBody = FileUtil.readResource("$mockDataPath/getMarketAll-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val isDetails = true

        // When
        val call = apiService.getMarketAll(isDetails)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 캔들 조회 - 분(Minute) 캔들 - Success")
    fun getCandlesMinutesSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getCandlesMinutes-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val unit: CandleUnit = CandleUnit.UNIT_60
        val market = "KRW-BTC"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val call = apiService.getCandlesMinutes(unit, market, to, count)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 캔들 조회 - 일(Day) 캔들 - Success")
    fun getCandlesDaysSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getCandlesDays-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3
        val convertingPriceUnit: String? = "KRW"

        // When
        val call = apiService.getCandlesDays(market, to, count, convertingPriceUnit)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 캔들 조회 - 주(Week) 캔들 - Success")
    fun getCandlesWeeksSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getCandlesWeeks-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val call = apiService.getCandlesWeeks(market, to, count)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 캔들 조회 - 월(Month) - Success")
    fun getCandlesMonthsSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getCandlesMonths-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val call = apiService.getCandlesMonths(market, to, count)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 체결 조회 - 최근 체결 내역 - Success")
    fun getTradesTicksSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getTradesTicks-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val market = "KRW-BTC"
        val to: String? = "001400"
        val count: Int? = 3
        val cursor: String? = null
        val daysAgo: Int? = 2

        // When
        val call = apiService.getTradesTicks(market, to, count, cursor, daysAgo)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 Ticker 조회 - 현재가 정보 - Success")
    fun getTickerSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getTicker-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val markets = "KRW-BTC, KRW-ETH"

        // When
        val call = apiService.getTicker(markets)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 호가 정보(Orderbook) 조회 - 호가 정보 조회 - Success")
    fun getOrderbookSuccessTest() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getOrderbook-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val markets: List<String> = listOf(
            "KRW-BTC",
            "KRW-ETH"
        )

        // When
        val call = apiService.getOrderbook(markets)
        val response = call.execute()

        // Then
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Test
    @DisplayName("시세 호가 정보(Orderbook) 조회 - 호가 정보 조회 - Success - async")
    fun getOrderbookSuccessTest_async() {
        // Given
        val mockBody = FileUtil.readResource("$mockDataPath/getOrderbook-success.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))

        val markets: List<String> = listOf(
            "KRW-BTC",
            "KRW-ETH"
        )

        // When
        val call = apiService.getOrderbook(markets)
        call.enqueue(object : Callback<List<OrderBook>> {

            // Then
            override fun onResponse(call: Call<List<OrderBook>>, response: Response<List<OrderBook>>) {
                println(TestUtil.convertResToPrettyStr(response))
                Assertions.assertTrue(response.isSuccessful)
            }

            override fun onFailure(call: Call<List<OrderBook>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        Thread.sleep(1000L)
    }
}
