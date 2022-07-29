package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 호가 정보(Orderbook)
 */
@Serializable
data class OrderBook(

    /**
     * 마켓 코드
     */
    val market: String,

    /**
     * 호가 생성 시각
     */
    val timestamp: Long,

    /**
     * 호가 매도 총 잔량
     */
    @SerialName("total_ask_size")
    val totalAskSize: Double,

    /**
     * 호가 매수 총 잔량
     */
    @SerialName("total_bid_size")
    val totalBidSize: Double,

    /**
     * orderbook_unit 리스트에는 15호가 정보가 들어가며 차례대로 1호가, 2호가 ... 15호가의 정보를 담고 있습니다.
     */
    @SerialName("orderbook_units")
    val orderbookUnits: List<OrderbookUnit>
)
