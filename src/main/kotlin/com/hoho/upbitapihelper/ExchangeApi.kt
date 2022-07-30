package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.OpenApiKey
import com.hoho.upbitapihelper.dto.exchange.*
import com.hoho.upbitapihelper.service.ApiService
import com.hoho.upbitapihelper.util.RetrofitUtil
import retrofit2.Call

/**
 * 교환 API
 *
 * 주문 요청 - 초당 8회, 분당 200회,
 * 주문 요청 외 API - 초당 30회, 분당 900회
 */
object ExchangeApi {

    private val apiService = ApiService.instance

    /**
     * 자산 - 전체 계좌 조회
     *
     * 내가 보유한 자산 리스트를 보여줍니다.
     *
     * @param apiKey
     */
    @JvmStatic
    fun getAccounts(
        apiKey: OpenApiKey
    ): Call<List<Account>> = apiService.getAccounts(
        RetrofitUtil.getAuthToken(apiKey)
    )

    /**
     * 주문 - 주문 가능 정보
     *
     * 마켓별 주문 가능 정보를 확인한다.
     *
     * @param apiKey
     * @param market Market ID
     */
    @JvmStatic
    fun getOrdersChance(
        apiKey: OpenApiKey,
        market: String
    ): Call<OrdersChance> {
        val queryString = "market=$market"

        return apiService.getOrdersChance(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            market
        )
    }

    /**
     * 주문 - 개별 주문 조회
     *
     * 주문 UUID를 통해 개별 주문건을 조회한다.
     *
     * @param apiKey
     * @param uuid 주문 UUID
     */
    @JvmStatic
    fun getOrderByUuid(
        apiKey: OpenApiKey,
        uuid: String
    ): Call<Order> {
        val queryString = "uuid=$uuid"

        return apiService.getOrder(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            uuid,
            null
        )
    }

    /**
     * 주문 - 개별 주문 조회
     *
     * 조회용 사용자 지정 값을 통해 개별 주문건을 조회한다.
     *
     * @param apiKey
     * @param identifier 조회용 사용자 지정 값
     */
    @JvmStatic
    fun getOrderByIdentifier(
        apiKey: OpenApiKey,
        identifier: String
    ): Call<Order> {
        val queryString = "identifier=$identifier"

        return apiService.getOrder(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            null,
            identifier
        )
    }

    /**
     * 주문 - 주문 리스트 조회
     *
     * 주문 UUID 목록을 통해 주문 리스트를 조회한다.
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
     * @param apiKey
     * @param market 마켓 아이디
     * @param uuids 주문 UUID의 목록
     * @param state 주문 상태
     * @param states 주문 상태의 목록.
     *               미체결 주문(wait, watch)과 완료 주문(done, cancel)은 혼합하여 조회하실 수 없습니다.
     * @param page 페이지 수, default: 1
     * @param limit 요청 개수, default: 100
     * @param orderBy 정렬 방식
     */
    @JvmStatic
    @JvmOverloads
    fun getOrdersByUuid(
        apiKey: OpenApiKey,
        market: String? = null,
        uuids: List<String>? = null,
        state: OrderState? = null,
        states: List<OrderState>? = null,
        page: Int? = null,
        limit: Int? = null,
        orderBy: OrderBy? = null
    ): Call<List<Order>> {
        val sb = StringBuffer()

        if (market != null) {
            sb.append("&market=$market")
        }
        if (uuids != null) {
            for (uuid in uuids) {
                sb.append("&uuids[]=$uuid")
            }
        }
        if (state != null) {
            sb.append("&state=${state.toString().lowercase()}")
        }
        if (states != null) {
            for (state_ in states) {
                sb.append("&states[]=${state_.toString().lowercase()}")
            }
        }
        if (page != null) {
            sb.append("&page=$page")
        }
        if (limit != null) {
            sb.append("&limit=$limit")
        }
        if (orderBy != null) {
            sb.append("&order_by=${orderBy.toString().lowercase()}")
        }

        val queryString = sb.toString().replaceFirst("&", "")

        return apiService.getOrders(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            market,
            uuids,
            null,
            state,
            states,
            page,
            limit,
            orderBy
        )
    }

    /**
     * 주문 - 주문 리스트 조회
     *
     * 조회용 사용자 지정 값 목록을 통해 주문 리스트를 조회한다.
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
     * @param apiKey
     * @param market 마켓 아이디
     * @param identifiers 주문 identifier의 목록
     * @param state 주문 상태
     * @param states 주문 상태의 목록.
     *               미체결 주문(wait, watch)과 완료 주문(done, cancel)은 혼합하여 조회하실 수 없습니다.
     * @param page 페이지 수, default: 1
     * @param limit 요청 개수, default: 100
     * @param orderBy 정렬 방식
     */
    @JvmStatic
    @JvmOverloads
    fun getOrdersByIdentifier(
        apiKey: OpenApiKey,
        market: String? = null,
        identifiers: List<String>? = null,
        state: OrderState? = null,
        states: List<OrderState>? = null,
        page: Int? = null,
        limit: Int? = null,
        orderBy: OrderBy? = null
    ): Call<List<Order>> {
        val sb = StringBuffer()

        if (market != null) {
            sb.append("&market=$market")
        }
        if (identifiers != null) {
            for (identifier in identifiers) {
                sb.append("&identifiers[]=$identifier")
            }
        }
        if (state != null) {
            sb.append("&state=${state.toString().lowercase()}")
        }
        if (states != null) {
            for (state_ in states) {
                sb.append("&states[]=${state_.toString().lowercase()}")
            }
        }
        if (page != null) {
            sb.append("&page=$page")
        }
        if (limit != null) {
            sb.append("&limit=$limit")
        }
        if (orderBy != null) {
            sb.append("&order_by=${orderBy.toString().lowercase()}")
        }

        val queryString = sb.toString().replaceFirst("&", "")

        return apiService.getOrders(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            market,
            null,
            identifiers,
            state,
            states,
            page,
            limit,
            orderBy
        )
    }

    /**
     * 주문 - 주문 취소 접수
     *
     * 주문 UUID를 통해 해당 주문에 대한 취소 접수를 한다.
     *
     * @param apiKey
     * @param uuid 주문 UUID
     */
    @JvmStatic
    fun deleteOrderByUuid(
        apiKey: OpenApiKey,
        uuid: String
    ): Call<Order> {
        val queryString = "uuid=$uuid"

        return apiService.deleteOrder(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            uuid,
            null
        )
    }

    /**
     * 주문 - 주문 취소 접수
     *
     * 조회용 사용자 지정 값을 통해 해당 주문에 대한 취소 접수를 한다.
     *
     * @param apiKey
     * @param identifier 조회용 사용자 지정 값
     */
    @JvmStatic
    fun deleteOrderByIdentifier(
        apiKey: OpenApiKey,
        identifier: String
    ): Call<Order> {
        val queryString = "identifier=$identifier"

        return apiService.deleteOrder(
            RetrofitUtil.getAuthToken(apiKey, queryString),
            null,
            identifier
        )
    }

    /**
     * 주문 - 주문하기 - 지정가 매수
     *
     * 주문 요청을 한다.
     *
     * @param apiKey
     * @param market 마켓 ID (필수)
     * @param price 주문 가격. (필수)
     * @param volume 주문량 (필수)
     * @param identifier 조회용 사용자 지정 값(Unique 값 사용) (선택)
     */
    @JvmStatic
    @JvmOverloads
    fun postOrdersBidLimit(
        apiKey: OpenApiKey,
        market: String,
        price: String,
        volume: String,
        identifier: String? = null
    ): Call<Order> {
        val params = HashMap<String, String>()
        params["market"] = market
        params["side"] = OrderSide.BID.value
        params["ord_type"] = OrderType.LIMIT.value
        params["price"] = price
        params["volume"] = volume
        if (identifier != null) params["identifier"] = identifier

        return apiService.postOrders(
            RetrofitUtil.getAuthToken(apiKey, params),
            params
        )
    }

    /**
     * 주문 - 주문하기 - 지정가 매도
     *
     * 주문 요청을 한다.
     *
     * @param apiKey
     * @param market 마켓 ID (필수)
     * @param price 주문 가격. (필수)
     * @param volume 주문량 (필수)
     * @param identifier 조회용 사용자 지정 값(Unique 값 사용) (선택)
     */
    @JvmStatic
    @JvmOverloads
    fun postOrdersAskLimit(
        apiKey: OpenApiKey,
        market: String,
        price: String,
        volume: String,
        identifier: String? = null
    ): Call<Order> {
        val params = HashMap<String, String>()
        params["market"] = market
        params["side"] = OrderSide.ASK.value
        params["ord_type"] = OrderType.LIMIT.value
        params["price"] = price
        params["volume"] = volume
        if (identifier != null) params["identifier"] = identifier

        return apiService.postOrders(
            RetrofitUtil.getAuthToken(apiKey, params),
            params
        )
    }

    /**
     * 주문 - 주문하기 - 시장가 매수
     *
     * 주문 요청을 한다.
     *
     * @param apiKey
     * @param market 마켓 ID (필수)
     * @param price 주문 가격. (필수)
     * @param identifier 조회용 사용자 지정 값(Unique 값 사용) (선택)
     */
    @JvmStatic
    @JvmOverloads
    fun postOrdersBidPrice(
        apiKey: OpenApiKey,
        market: String,
        price: String,
        identifier: String? = null
    ): Call<Order> {
        val params = HashMap<String, String>()
        params["market"] = market
        params["side"] = OrderSide.BID.value
        params["ord_type"] = OrderType.PRICE.value
        params["price"] = price
        if (identifier != null) params["identifier"] = identifier

        return apiService.postOrders(
            RetrofitUtil.getAuthToken(apiKey, params),
            params
        )
    }

    /**
     * 주문 - 주문하기 - 시장가 매도
     *
     * 주문 요청을 한다.
     *
     * @param apiKey
     * @param market 마켓 ID (필수)
     * @param volume 주문량 (필수)
     * @param identifier 조회용 사용자 지정 값(Unique 값 사용) (선택)
     */
    @JvmStatic
    @JvmOverloads
    fun postOrdersAskMarket(
        apiKey: OpenApiKey,
        market: String,
        volume: String,
        identifier: String? = null
    ): Call<Order> {
        val params = HashMap<String, String>()
        params["market"] = market
        params["side"] = OrderSide.ASK.value
        params["ord_type"] = OrderType.MARKET.value
        params["volume"] = volume
        if (identifier != null) params["identifier"] = identifier

        return apiService.postOrders(
            RetrofitUtil.getAuthToken(apiKey, params),
            params
        )
    }

    /**
     * 서비스 정보 - 입출금 현황
     *
     * 입출금 현황 및 블록 상태를 조회합니다.
     *
     * 🚧 입출금 현황 데이터는 실제 서비스 상태와 다를 수 있습니다.
     * 입출금 현황 API에서 제공하는 입출금 상태, 블록 상태 정보는 수 분 정도 지연되어 반영될 수 있습니다.
     * 본 API는 참고용으로만 사용하시길 바라며 실제 입출금을 수행하기 전에는 반드시 업비트 공지사항 및 입출금 현황 페이지를 참고해주시기 바랍니다.
     *
     * @param apiKey
     */
    @JvmStatic
    fun getWalletStatus(
        apiKey: OpenApiKey
    ): Call<List<WalletStatus>> = apiService.getWalletStatus(
        RetrofitUtil.getAuthToken(apiKey)
    )

    /**
     * 서비스 정보 - API 키 리스트 조회
     *
     * API 키 목록 및 만료 일자를 조회합니다.
     *
     * @param apiKey
     */
    @JvmStatic
    fun getApiKeys(
        apiKey: OpenApiKey
    ): Call<List<ApiKey>> = apiService.getApiKeys(
        RetrofitUtil.getAuthToken(apiKey)
    )
}
