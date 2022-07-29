package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 계좌
 */
@Serializable
data class OrdersChance(

    /**
     * 매수 수수료 비율
     *
     * NumberString
     */
    @SerialName("bid_fee")
    val bidFee: String,

    /**
     * 매도 수수료 비율
     *
     * NumberString
     */
    @SerialName("ask_fee")
    val askFee: String,

    /**
     * FIXME 문서에 없음
     *
     * NumberString
     */
    @SerialName("maker_bid_fee")
    val markerBidFee: String,

    /**
     * FIXME 문서에 없음
     *
     * NumberString
     */
    @SerialName("maker_ask_fee")
    val makerAskFee: String,

    /**
     * 마켓에 대한 정보
     */
    val market: Market,

    /**
     * 매수 시 사용하는 화폐의 계좌 상태
     */
    @SerialName("bid_account")
    val bidAccount: BidAccount,

    /**
     * 매도 시 사용하는 화폐의 계좌 상태
     */
    @SerialName("ask_account")
    val askAccount: AskAccount
) {

    /**
     * 마켓에 대한 정보
     */
    @Serializable
    data class Market(

        /**
         * 마켓의 유일 키
         */
        val id: String,

        /**
         * 마켓 이름
         */
        val name: String,

        /**
         * 지원 주문 방식
         */
        @SerialName("order_types")
        val orderTypes: List<OrderType>,

        /**
         * 지원 주문 종류
         */
        @SerialName("order_sides")
        val orderSides: List<OrderSide>,

        /**
         * 매수 시 제약사항
         */
        val bid: Bid,

        /**
         * 매도 시 제약사항
         */
        val ask: Ask,

        /**
         * 최대 매도/매수 금액
         *
         * NumberString
         */
        @SerialName("max_total")
        val maxTotal: Double,

        /**
         * 마켓 운영 상태
         */
        @SerialName("state")
        val state: MarketState
    ) {

        /**
         * 매수 시 제약사항
         */
        @Serializable
        data class Bid(

            /**
             * 화폐를 의미하는 영문 대문자 코드
             */
            val currency: String,

            /**
             * 주문금액 단위
             */
            @SerialName("price_unit")
            val priceUnit: String? = null,

            /**
             * 최소 매도/매수 금액
             */
            @SerialName("min_total")
            val minTotal: Double
        )

        /**
         * 매도 시 제약사항
         */
        @Serializable
        data class Ask(

            /**
             * 화폐를 의미하는 영문 대문자 코드
             */
            val currency: String,

            /**
             * 주문금액 단위
             */
            @SerialName("price_unit")
            val priceUnit: String? = null,

            /**
             * 최소 매도/매수 금액
             */
            @SerialName("min_total")
            val minTotal: Double
        )

        /**
         * 마켓 상태
         */
        @Serializable
        enum class MarketState {

            @SerialName("active")
            ACTIVE
        }
    }

    /**
     * 매수 시 사용하는 화폐의 계좌 상태
     */
    @Serializable
    data class BidAccount(

        /**
         * 화폐를 의미하는 영문 대문자 코드
         */
        val currency: String,

        /**
         * 주문가능 금액/수량
         *
         * NumberString
         */
        val balance: String,

        /**
         * 주문 중 묶여있는 금액/수량
         *
         * NumberString
         */
        val locked: String,

        /**
         * 매수평균가
         *
         * NumberString
         */
        @SerialName("avg_buy_price")
        val avgBuyPrice: String,

        /**
         * 매수평균가 수정 여부
         */
        @SerialName("avg_buy_price_modified")
        val avgBuyPriceModified: Boolean,

        /**
         * 평단가 기준 화폐
         */
        @SerialName("unit_currency")
        val unitCurrency: String
    )

    /**
     * 매도 시 사용하는 화폐의 계좌 상태
     */
    @Serializable
    data class AskAccount(

        /**
         * 화폐를 의미하는 영문 대문자 코드
         */
        val currency: String,

        /**
         * 주문가능 금액/수량
         *
         * NumberString
         */
        val balance: String,

        /**
         * 주문 중 묶여있는 금액/수량
         *
         * NumberString
         */
        val locked: String,

        /**
         * 매수평균가
         *
         * NumberString
         */
        @SerialName("avg_buy_price")
        val avgBuyPrice: String,

        /**
         * 매수평균가 수정 여부
         */
        @SerialName("avg_buy_price_modified")
        val avgBuyPriceModified: Boolean,

        /**
         * 평단가 기준 화폐
         */
        @SerialName("unit_currency")
        val unitCurrency: String
    )
}