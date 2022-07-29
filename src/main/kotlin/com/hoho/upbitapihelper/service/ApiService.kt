package com.hoho.upbitapihelper.service

import com.hoho.upbitapihelper.dto.exchange.*
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
import retrofit2.http.*

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
    ===  EXCHANGE API  ===============
    ==================================
     */

    /**
     * 자산 - 전체 계좌 조회
     *
     * 내가 보유한 자산 리스트를 보여줍니다.
     *
     * @param token Authorization token (JWT)
     */
    @GET("accounts")
    fun getAccounts(
        @Header("Authorization") token: String
    ): Call<List<Account>>

    /**
     * 주문 - 주문 가능 정보
     *
     * 마켓별 주문 가능 정보를 확인한다.
     *
     * @param token Authorization token (JWT)
     * @param market Market ID
     */
    @GET("orders/chance")
    fun getOrdersChance(
        @Header("Authorization") token: String,
        @Query("market") market: String
    ): Call<OrdersChance>

    /**
     * 주문 - 개별 주문 조회
     *
     * 주문 UUID or 조회용 사용자 지정 값을 통해 개별 주문건을 조회한다.
     *
     * 🚧 uuid 혹은 identifier 둘 중 하나의 값이 반드시 포함되어야 합니다.
     *
     * @param token Authorization token (JWT)
     * @param uuid 주문 UUID
     * @param identifier 조회용 사용자 지정 값
     */
    @GET("order")
    fun getOrder(
        @Header("Authorization") token: String,
        @Query("uuid") uuid: String?,
        @Query("identifier") identifier: String?
    ): Call<Order>

    /**
     * 주문 - 주문 리스트 조회
     *
     * 주문 리스트를 조회한다.
     *
     * 🚧 states 파라미터 변경 안내 (2021. 03. 22 ~)
     * 2021년 3월 22일부터 미체결 주문(wait, watch)과 완료 주문(done, cancel)을 혼합하여 조회하실 수 없습니다.
     *
     * 예시1) done, cancel 주문을 한 번에 조회 => 가능
     *
     * 예시2) wait, done 주문을 한 번에 조회 => 불가능 (각각 API 호출 필요)
     *
     * 자세한 내용은 개발자 센터 공지사항을 참조 부탁드립니다.
     *
     * uuids와 identifiers 동시에 쿼리 불가
     *
     * @param token Authorization token (JWT)
     * @param market 마켓 아이디
     * @param uuids 주문 UUID의 목록
     * @param identifiers 주문 identifier의 목록
     * @param state 주문 상태
     * @param states 주문 상태의 목록.
     *               미체결 주문(wait, watch)과 완료 주문(done, cancel)은 혼합하여 조회하실 수 없습니다.
     * @param page 페이지 수, default: 1
     * @param limit 요청 개수, default: 100
     * @param orderBy 정렬 방식
     */
    @GET("orders")
    fun getOrders(
        @Header("Authorization") token: String,
        @Query("market") market: String?,
        @Query("uuids[]") uuids: List<String>?,
        @Query("identifiers[]") identifiers: List<String>?,
        @Query("state") state: OrderState?,
        @Query("states[]") states: List<@JvmSuppressWildcards OrderState>?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("order_by") orderBy: OrderBy?
    ): Call<List<Order>>

    /**
     * 주문 - 주문 취소 접수
     *
     * 주문 UUID or 조회용 사용자 지정 값을 통해 해당 주문에 대한 취소 접수를 한다.
     *
     * 🚧 uuid 혹은 identifier 둘 중 하나의 값이 반드시 포함되어야 합니다.
     *
     * @param token Authorization token (JWT)
     * @param uuid 취소할 주문의 UUID
     * @param identifier 조회용 사용자 지정 값
     */
    @DELETE("order")
    fun deleteOrder(
        @Header("Authorization") token: String,
        @Query("uuid") uuid: String?,
        @Query("identifier") identifier: String?
    ): Call<Order>

    /**
     * 서비스 정보 - 입출금 현황
     *
     * 입출금 현황 및 블록 상태를 조회합니다.
     *
     * 🚧 입출금 현황 데이터는 실제 서비스 상태와 다를 수 있습니다.
     * 입출금 현황 API에서 제공하는 입출금 상태, 블록 상태 정보는 수 분 정도 지연되어 반영될 수 있습니다.
     * 본 API는 참고용으로만 사용하시길 바라며 실제 입출금을 수행하기 전에는 반드시 업비트 공지사항 및 입출금 현황 페이지를 참고해주시기 바랍니다.
     *
     * @param token Authorization token (JWT)
     */
    @GET("status/wallet")
    fun getWalletStatus(
        @Header("Authorization") token: String
    ): Call<List<WalletStatus>>

    /**
     * 서비스 정보 - API 키 리스트 조회
     *
     * API 키 목록 및 만료 일자를 조회합니다.
     *
     * @param token Authorization token (JWT)
     */
    @GET("api_keys")
    fun getApiKeys(
        @Header("Authorization") token: String
    ): Call<List<ApiKey>>

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
