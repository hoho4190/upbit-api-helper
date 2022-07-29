package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 호가 Unit
 */
@Serializable
data class OrderbookUnit(

    /**
     * 매도호가
     */
    @SerialName("ask_price")
    val askPrice: Double,

    /**
     * 매수호가
     */
    @SerialName("bid_price")
    val bidPrice: Double,

    /**
     * 매도 잔량
     */
    @SerialName("ask_size")
    val askSize: Double,

    /**
     * 매수 잔량
     */
    @SerialName("bid_size")
    val bidSize: Double
)
