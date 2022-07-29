package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 계좌
 */
@Serializable
data class Account(

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