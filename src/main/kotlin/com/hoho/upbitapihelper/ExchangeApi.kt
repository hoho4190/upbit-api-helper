package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.OpenApiKey
import com.hoho.upbitapihelper.dto.exchange.Account
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
}
