package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 주문 타입
 */
@Serializable
enum class OrderType(val value: String) {

    /**
     * 지정가 주문
     */
    @SerialName("limit")
    LIMIT("limit"),

    /**
     * 시장가 주문(매수)
     */
    @SerialName("price")
    PRICE("price"),

    /**
     * 시장가 주문(매도)
     */
    @SerialName("market")
    MARKET("market")
}