package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.OpenApiKey
import com.hoho.upbitapihelper.dto.exchange.Account
import com.hoho.upbitapihelper.dto.exchange.ApiKey
import com.hoho.upbitapihelper.dto.exchange.WalletStatus
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
