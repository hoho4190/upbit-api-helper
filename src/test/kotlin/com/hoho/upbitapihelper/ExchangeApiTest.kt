package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.OpenApiKey
import com.hoho.upbitapihelper.dto.exchange.OrderBy
import com.hoho.upbitapihelper.dto.exchange.OrderState
import com.hoho.upbitapihelper.util.TestUtil
import org.junit.jupiter.api.*
import java.util.*

@Disabled
@DisplayName("E2E Test - Exchange API")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExchangeApiTest {

    private lateinit var openApiKey: OpenApiKey

    @BeforeAll
    fun beforeAll() {
        openApiKey = TestUtil.getOpenApiKey()
    }

    @Disabled
    @Test
    @DisplayName("자산 - 전체 계좌 조회")
    fun getAccountsTest() {
        // Given

        // When
        val call = ExchangeApi.getAccounts(openApiKey)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertResToPrettyStr(response))
        } else {
            println(TestUtil.convertResToPrettyStr(response))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 주문 가능 정보")
    fun getOrdersChanceTest() {
        // Given
        val market = "KRW-BTC"

        // When
        val call = ExchangeApi.getOrdersChance(openApiKey, market)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 개별 주문 조회(UUID)")
    fun getOrderByUuidTest() {
        // Given
        val uuid = "uuid"

        // When
        val call = ExchangeApi.getOrderByUuid(openApiKey, uuid)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 개별 주문 조회(identifier)")
    fun getOrderByIdentifierTest() {
        // Given
        val identifier = "identifier"

        // When
        val call = ExchangeApi.getOrderByIdentifier(openApiKey, identifier)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 주문 리스트 조회(UUID)")
    fun getOrdersByUuidTest() {
        // Given
        var market: String? = null
        var uuids: List<String>? = null
        var state: OrderState? = null
        var states: List<OrderState>? = null
        var page: Int? = null
        var limit: Int? = null
        var orderBy: OrderBy? = null

        market = "KRW-BTC"
//        uuids = listOf(
//            "uuid1",
//            "uuid2"
//        )
//        state = OrderState.WATCH
        states = listOf(
            OrderState.CANCEL,
            OrderState.DONE
        )
//        states  = listOf(
//            OrderState.WAIT,
//            OrderState.WATCH
//        )
        page = 1
        limit = 5
        orderBy = OrderBy.DESC

        // When
        val call = ExchangeApi.getOrdersByUuid(
            openApiKey,
            market, uuids, state, states, page, limit, orderBy
        )
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertResToPrettyStr(response))
        } else {
            println(TestUtil.convertResToPrettyStr(response))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 주문 리스트 조회(identifier)")
    fun getOrdersByIdentifierTest() {
        // Given
        var market: String? = null
        var identifiers: List<String>? = null
        var state: OrderState? = null
        var states: List<OrderState>? = null
        var page: Int? = null
        var limit: Int? = null
        var orderBy: OrderBy? = null

        market = "KRW-BTC"
        identifiers = listOf(
            "identifiers1",
        )
        state = OrderState.CANCEL
//        states  = listOf(
//            OrderState.CANCEL,
//            OrderState.DONE
//        )
//        states  = listOf(
//            OrderState.WAIT,
//            OrderState.WATCH
//        )
//        page = 1
//        limit = 100
//        orderBy = OrderBy.ASC

        // When
        val call = ExchangeApi.getOrdersByIdentifier(
            openApiKey,
            market, identifiers, state, states, page, limit, orderBy
        )
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertResToPrettyStr(response))
        } else {
            println(TestUtil.convertResToPrettyStr(response))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 주문 취소 접수(UUID)")
    fun deleteOrderByUuidTest() {
        // Given
        val uuid = "uuid"

        // When
        val call = ExchangeApi.deleteOrderByUuid(openApiKey, uuid)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 주문 취소 접수(identifier)")
    fun deleteOrderByIdentifierTest() {
        // Given
        val identifier = "identifier"

        // When
        val call = ExchangeApi.deleteOrderByIdentifier(openApiKey, identifier)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("주문 - 주문하기 - 지정가 매수")
    fun postOrdersBidLimitTest() {
        // Given
        val market = "KRW-BTC"
        val price = "25000000"
        val volume = "0.0003"
        val identifier: String = UUID.randomUUID().toString()
        println("지정가 매수 identifier=$identifier")

        // When
        val call = ExchangeApi.postOrdersBidLimit(openApiKey, market, price, volume, identifier)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    /**
     * 지정가 매도
     */
    @Disabled
    @Test
    @DisplayName("주문 - 주문하기 - 지정가 매도")
    fun postOrdersAskLimitTest() {
        // Given
        val market = "KRW-BTC"
        val price = "50000000"
        val volume = "0.00019184"
        val identifier: String = UUID.randomUUID().toString()
        println("지정가 매도 identifier=$identifier")

        // When
        val call = ExchangeApi.postOrdersAskLimit(openApiKey, market, price, volume, identifier)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    /**
     * 시장가 매수
     */
    @Disabled
    @Test
    @DisplayName("주문 - 주문하기 - 시장가 매수")
    fun postOrdersBidPriceTest() {
        // Given
        val market = "KRW-BTC"
        val price = "6000"
        val identifier: String = UUID.randomUUID().toString()
        println("시장가 매수 identifier=$identifier")

        // When
        val call = ExchangeApi.postOrdersBidPrice(openApiKey, market, price, identifier)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    /**
     * 시장가 매도
     */
    @Disabled
    @Test
    @DisplayName("주문 - 주문하기 - 시장가 매도")
    fun postOrdersAskMarketTest() {
        // Given
        val market = "KRW-BTC"
        val volume = "0.00019184"
        val identifier: String = UUID.randomUUID().toString()
        println("시장가 매도 identifier=$identifier")

        // When
        val call = ExchangeApi.postOrdersAskMarket(openApiKey, market, volume, identifier)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        println(TestUtil.convertResToPrettyStr(response))
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("서비스 정보 - 입출금 현황")
    fun getWalletStatusTest() {
        // Given

        // When
        val call = ExchangeApi.getWalletStatus(openApiKey)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertResToPrettyStr(response))
        } else {
            println(TestUtil.convertResToPrettyStr(response))
        }
        Assertions.assertTrue(response.isSuccessful)
    }

    @Disabled
    @Test
    @DisplayName("서비스 정보 - API 키 리스트 조회")
    fun getApiKeysTest() {
        // Given

        // When
        val call = ExchangeApi.getApiKeys(openApiKey)
        val response = call.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertResToPrettyStr(response))
        } else {
            println(TestUtil.convertResToPrettyStr(response))
        }
        Assertions.assertTrue(response.isSuccessful)
    }
}
