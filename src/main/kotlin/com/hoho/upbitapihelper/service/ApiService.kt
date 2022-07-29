package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.quotation.*
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
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 업비트 API
 *
 * 호가 API
 *
 * Websocket 연결 요청 수 제한 - 초당 5회, 분당 100회,
 * REST API 요청 수 제한 - 분당 600회, 초당 10회 (종목, 캔들, 체결, 티커, 호가별)
 */
internal interface ApiService {

    companion object {
        @JvmStatic
        @OptIn(ExperimentalSerializationApi::class)
        val instance: ApiService by lazy {
            Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
                .addConverterFactory(EnumConverterFactory())
                .baseUrl(RetrofitUtil.apiUrlInfo.api)
                .build()
                .create()
        }
    }

    /*
    ==================================
    ===  QUOTATION API  ==============
    ==================================
     */

    /**
     * 시세 종목 조회 - 마켓 코드 조회
     *
     * 업비트에서 거래 가능한 마켓 목록
     *
     * @param isDetails 유의종목 필드과 같은 상세 정보 노출 여부(선택 파라미터)
     */
    @GET("market/all")
    fun getMarketAll(
        @Query("isDetails") isDetails: Boolean?
    ): Call<List<Market>>

    /**
     * 시세 캔들 조회 - 분(Minute) 캔들
     *
     * @param unit 분 단위. 가능한 값 : 1, 3, 5, 15, 10, 30, 60, 240
     * @param market 마켓 코드 (ex. KRW-BTC)
     * @param to 마지막 캔들 시각 (exclusive).
     *           포맷 : yyyy-MM-dd'T'HH:mm:ss'Z' or yyyy-MM-dd HH:mm:ss.
     *           비워서 요청시 가장 최근 캔들
     * @param count 캔들 개수(최대 200개까지 요청 가능)
     */
    @GET("candles/minutes/{unit}")
    fun getCandlesMinutes(
        @Path("unit") unit: CandleUnit,
        @Query("market") market: String,
        @Query("to") to: String?,
        @Query("count") count: Int?
    ): Call<List<Candle>>

    /**
     * 시세 캔들 조회 - 일(Day) 캔들
     *
     * convertingPriceUnit 파라미터의 경우,
     * 원화 마켓이 아닌 다른 마켓(ex. BTC, ETH)의 일봉 요청시 종가를 명시된
     * 파라미터 값으로 환산해 converted_trade_price 필드에 추가하여 반환합니다.
     * 현재는 원화(KRW) 로 변환하는 기능만 제공하며 추후 기능을 확장할 수 있습니다.
     *
     * @param market 마켓 코드 (ex. KRW-BTC, BTC-BCC)
     * @param to 마지막 캔들 시각 (exclusive).
     *           포맷 : yyyy-MM-dd'T'HH:mm:ss'Z' or yyyy-MM-dd HH:mm:ss.
     *           비워서 요청시 가장 최근 캔들
     * @param count 캔들 개수(최대 200개까지 요청 가능)
     * @param convertingPriceUnit 종가 환산 화폐 단위 (생략 가능, KRW로 명시할 시 원화 환산 가격을 반환.)
     */
    @GET("candles/days")
    fun getCandlesDays(
        @Query("market") market: String,
        @Query("to") to: String?,
        @Query("count") count: Int?,
        @Query("convertingPriceUnit") convertingPriceUnit: String?
    ): Call<List<Candle>>

    /**
     * 시세 캔들 조회 - 주(Week) 캔들
     *
     * @param market 마켓 코드 (ex. KRW-BTC)
     * @param to 마지막 캔들 시각 (exclusive).
     *           포맷 : yyyy-MM-dd'T'HH:mm:ss'Z' or yyyy-MM-dd HH:mm:ss.
     *           비워서 요청시 가장 최근 캔들
     * @param count 캔들 개수(최대 200개까지 요청 가능)
     */
    @GET("candles/weeks")
    fun getCandlesWeeks(
        @Query("market") market: String,
        @Query("to") to: String?,
        @Query("count") count: Int?
    ): Call<List<Candle>>

    /**
     * 시세 캔들 조회 - 월(Month) 캔들
     *
     * @param market 마켓 코드 (ex. KRW-BTC)
     * @param to 마지막 캔들 시각 (exclusive).
     *           포맷 : yyyy-MM-dd'T'HH:mm:ss'Z' or yyyy-MM-dd HH:mm:ss.
     *           비워서 요청시 가장 최근 캔들
     * @param count 캔들 개수(최대 200개까지 요청 가능)
     */
    @GET("candles/months")
    fun getCandlesMonths(
        @Query("market") market: String,
        @Query("to") to: String?,
        @Query("count") count: Int?
    ): Call<List<Candle>>

    /**
     * 시세 체결 조회 - 최근 체결 내역
     *
     * @param market 마켓 코드 (ex. KRW-BTC)
     * @param to 마지막 체결 시각.
     *           형식 : [HHmmss 또는 HH:mm:ss].
     *           비워서 요청시 가장 최근 데이터
     * @param count 체결 개수
     * @param cursor 페이지네이션 커서 (sequentialId)
     * @param daysAgo 최근 체결 날짜 기준 7일 이내의 이전 데이터 조회 가능.
     *                비워서 요청 시 가장 최근 체결 날짜 반환. (범위: 1 ~ 7))
     */
    @GET("trades/ticks")
    fun getTradesTicks(
        @Query("market") market: String,
        @Query("to") to: String?,
        @Query("count") count: Int?,
        @Query("cursor") cursor: String?,
        @Query("daysAgo") daysAgo: Int?
    ): Call<List<Tick>>

    /**
     * 시세 Ticker 조회 - 현재가 정보
     *
     * 요청 당시 종목의 스냅샷을 반환한다.
     *
     * @param markets 반점으로 구분되는 마켓 코드 (ex. KRW-BTC, KRW-ETH)
     */
    @GET("ticker")
    fun getTicker(
        @Query("markets") markets: String
    ): Call<List<Ticker>>

    /**
     * 시세 호가 정보(Orderbook) 조회 - 호가 정보 조회
     *
     * @param markets 마켓 코드 목록 (ex. KRW-BTC,BTC-ETH)
     */
    @GET("orderbook")
    fun getOrderbook(
        @Query("markets") markets: List<String>
    ): Call<List<OrderBook>>
}
