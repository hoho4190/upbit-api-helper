package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.ErrorResponse
import com.hoho.upbitapihelper.dto.quotation.CandleUnit
import com.hoho.upbitapihelper.util.RetrofitUtil
import com.hoho.upbitapihelper.util.TestUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Disabled
@DisplayName("E2E Test - Quotation API")
internal class QuotationApiTest {

    @Disabled
    @Test
    @DisplayName("시세 종목 조회 - 마켓 코드 조회")
    fun getMarketAllTest() {
        // Given
        val isDetails = true

        // When
        val call = QuotationApi.getMarketAll(isDetails)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 캔들 조회 - 분(Minute) 캔들")
    fun getCandlesMinutesTest() {
        // Given
        val unit: CandleUnit = CandleUnit.UNIT_60
        val market = "KRW-BTC"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val call = QuotationApi.getCandlesMinutes(unit, market, to, count)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 캔들 조회 - 일(Day) 캔들")
    fun getCandlesDaysTest() {
        // Given
        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3
        val convertingPriceUnit: String? = "KRW"

        // When
        val call = QuotationApi.getCandlesDays(market, to, count, convertingPriceUnit)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 캔들 조회 - 주(Week) 캔들")
    fun getCandlesWeeksTest() {
        // Given
        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val call = QuotationApi.getCandlesWeeks(market, to, count)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 캔들 조회 - 월(Month) 캔들")
    fun getCandlesMonths() {
        // Given
        val market = "BTC-ETH"
        val to: String? = "2022-07-29T00:03:00+09:00"
        val count: Int? = 3

        // When
        val call = QuotationApi.getCandlesMonths(market, to, count)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 체결 조회 - 최근 체결 내역")
    fun getTradesTicksTest() {
        // Given
        val market = "KRW-BTC"
        val to: String? = "001400"
        val count: Int? = 3
        val cursor: String? = null
        val daysAgo: Int? = 2

        // When
        val call = QuotationApi.getTradesTicks(market, to, count, cursor, daysAgo)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 Ticker 조회 - 현재가 정보")
    fun getTickerTest() {
        // Given
        val markets = "KRW-BTC, KRW-ETH"

        // When
        val call = QuotationApi.getTicker(markets)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("시세 호가 정보(Orderbook) 조회 - 호가 정보 조회")
    fun getOrderbookTest() {
        // Given
        val markets: List<String> = listOf(
            "KRW-BTC",
            "KRW-ETH"
        )

        // When
        val call = QuotationApi.getOrderbook(markets)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }
}
