package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 주문 종류
 */
@Serializable
enum class OrderSide(val value: String) {

    /**
     * 매수
     */
    @SerialName("bid")
    BID("bid"),

    /**
     * 매도
     */
    @SerialName("ask")
    ASK("ask")
}