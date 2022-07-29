package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Ticker
 */
@Serializable
data class Ticker(

    /**
     * 종목 구분 코드
     */
    val market: String,

    /**
     * 최근 거래 일자(UTC)
     */
    @SerialName("trade_date")
    val tradeDate: String,

    /**
     * 최근 거래 시각(UTC)
     */
    @SerialName("trade_time")
    val tradeTime: String,

    /**
     * 최근 거래 일자(KST)
     */
    @SerialName("trade_date_kst")
    val tradeDateKst: String,

    /**
     * 최근 거래 시각(KST)
     */
    @SerialName("trade_time_kst")
    val tradeTimeKst: String,

    /**
     * FIXME 문서에 없음
     */
    @SerialName("trade_timestamp")
    val tradeTimestamp: Long,

    /**
     * 시가
     */
    @SerialName("opening_price")
    val openingPrice: Double,

    /**
     * 고가
     */
    @SerialName("high_price")
    val highPrice: Double,

    /**
     * 저가
     */
    @SerialName("low_price")
    val lowPrice: Double,

    /**
     * 종가(현재가)
     */
    @SerialName("trade_price")
    val tradePrice: Double,

    /**
     * 전일 종가(UTC 0시 기준)
     */
    @SerialName("prev_closing_price")
    val prevClosingPrice: Double,

    /**
     * 보합/상승/하락
     */
    val change: TickerChange,

    /**
     * 변화액의 절대값
     */
    @SerialName("change_price")
    val changePrice: Double,

    /**
     * 변화율의 절대값
     */
    @SerialName("change_rate")
    val changeRate: Double,

    /**
     * 부호가 있는 변화액
     */
    @SerialName("signed_change_price")
    val signedChangePrice: Double,

    /**
     * 부호가 있는 변화율
     */
    @SerialName("signed_change_rate")
    val signedChangeRate: Double,

    /**
     * 가장 최근 거래량
     */
    @SerialName("trade_volume")
    val tradeVolume: Double,

    /**
     * 누적 거래대금(UTC 0시 기준)
     */
    @SerialName("acc_trade_price")
    val accTradePrice: Double,

    /**
     * 24시간 누적 거래대금
     */
    @SerialName("acc_trade_price_24h")
    val accTradePrice24h: Double,

    /**
     * 누적 거래량(UTC 0시 기준)
     */
    @SerialName("acc_trade_volume")
    val accTradeVolume: Double,

    /**
     * 24시간 누적 거래량
     */
    @SerialName("acc_trade_volume_24h")
    val accTradeVolume24h: Double,

    /**
     * 52주 신고가
     */
    @SerialName("highest_52_week_price")
    val highest_52_week_price: Double,

    /**
     * 52주 신고가 달성일
     */
    @SerialName("highest_52_week_date")
    val highest52WeekDate: String,

    /**
     * 52주 신저가
     */
    @SerialName("lowest_52_week_price")
    val lowest52WeekPrice: Double,

    /**
     * 52주 신저가 달성일
     */
    @SerialName("lowest_52_week_date")
    val lowest52WeekDate: String,

    /**
     * 타임 스탬프
     */
    val timestamp: Long
)
