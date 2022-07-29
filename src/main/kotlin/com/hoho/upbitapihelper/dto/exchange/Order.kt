package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 주문
 */
@Serializable
data class Order(

    /**
     * 주문의 고유 아이디
     */
    val uuid: String,

    /**
     * 주문 종류
     */
    val side: OrderSide,

    /**
     * 주문 방식
     */
    @SerialName("ord_type")
    val ordType: OrderType,

    /**
     * 주문 당시 화폐 가격
     *
     * NumberString
     */
    val price: String? = null,

    /**
     * 체결 가격의 평균가
     *
     * NumberString
     */
    @SerialName("avg_price")
    val avgPrice: String? = null,

    /**
     * 주문 상태
     */
    val state: OrderState,

    /**
     * 마켓의 유일키
     */
    val market: String,

    /**
     * 주문 생성 시간
     *
     * DateString
     */
    @SerialName("created_at")
    val createdAt: String,

    /**
     * 사용자가 입력한 주문 양
     *
     * NumberString
     */
    val volume: String? = null,

    /**
     * 체결 후 남은 주문 양
     *
     * NumberString
     */
    @SerialName("remaining_volume")
    val remainingVolume: String? = null,

    /**
     * 수수료로 예약된 비용
     *
     * NumberString
     */
    @SerialName("reserved_fee")
    val reservedFee: String,

    /**
     * 남은 수수료
     *
     * NumberString
     */
    @SerialName("remaining_fee")
    val remainingFee: String,

    /**
     * 사용된 수수료
     *
     * NumberString
     */
    @SerialName("paid_fee")
    val paidFee: String,

    /**
     * 거래에 사용중인 비용
     *
     * NumberString
     */
    val locked: String,

    /**
     * 체결된 양
     *
     * NumberString
     */
    @SerialName("executed_volume")
    val executedVolume: String,

    /**
     * 해당 주문에 걸린 체결 수
     */
    @SerialName("trades_count")
    val tradesCount: Int,

    /**
     * 체결 목록
     */
    val trades: List<Trade>? = null
) {

    /**
     * 체결
     */
    @Serializable
    data class Trade(

        /**
         * 마켓의 유일 키
         */
        val market: String,

        /**
         * 체결의 고유 아이디
         */
        val uuid: String,

        /**
         * 체결 가격
         *
         * NumberString
         */
        val price: String? = null,

        /**
         * 체결 양
         *
         * NumberString
         */
        val volume: String,

        /**
         * 체결된 총 가격
         *
         * NumberString
         */
        val funds: String,

        /**
         * FIXME 문서에 없음
         */
        val trend: String? = null,

        /**
         * 체결 종류
         */
        val side: OrderSide,

        /**
         * 체결 시각
         *
         * DateString
         */
        @SerialName("created_at")
        val createdAt: String
    )
}
