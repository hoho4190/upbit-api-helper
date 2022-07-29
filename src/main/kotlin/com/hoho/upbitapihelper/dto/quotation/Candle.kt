package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 캔들
 */
@Serializable
data class Candle(

    /**
     * 마켓명
     */
    val market: String,

    /**
     * 캔들 기준 시각(UTC 기준)
     */
    @SerialName("candle_date_time_utc")
    val candleDateTimeUtc: String,

    /**
     * 캔들 기준 시각(KST 기준)
     */
    @SerialName("candle_date_time_kst")
    val candleDateTimeKst: String,

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
     * 종가
     */
    @SerialName("trade_price")
    val tradePrice: Double,

    /**
     * 해당 캔들에서 마지막 틱이 저장된 시각
     */
    val timestamp: Long,

    /**
     * 누적 거래 금액
     */
    @SerialName("candle_acc_trade_price")
    val candleAccTradePrice: Double,

    /**
     * 누적 거래량
     */
    @SerialName("candle_acc_trade_volume")
    val candleAccTradeVolume: Double,

    /**
     * 분 단위(유닛)
     *
     * 분(Minute) 캔들 리턴
     */
    val unit: Int? = null,

    /**
     * 전일 종가(UTC 0시 기준)
     *
     * 일(Day) 캔들 리턴
     */
    @SerialName("prev_closing_price")
    val prevClosingPrice: Double? = null,

    /**
     * 전일 종가 대비 변화 금액
     *
     * 일(Day) 캔들 리턴
     */
    @SerialName("change_price")
    val changePrice: Double? = null,

    /**
     * 전일 종가 대비 변화량
     *
     * 일(Day) 캔들 리턴
     */
    @SerialName("change_rate")
    val changeRate: Double? = null,

    /**
     * 종가 환산 화폐 단위로 환산된 가격
     * (요청에 convertingPriceUnit 파라미터 없을 시 해당 필드 포함되지 않음.)
     *
     * 일(Day) 캔들 리턴
     */
    @SerialName("converted_trade_price")
    val convertedTradePrice: Double? = null,

    /**
     * 캔들 기간의 가장 첫 날
     *
     * 주(Week) 캔들, 월(Month) 캔들 리턴
     */
    @SerialName("first_day_of_period")
    val firstDayOfPeriod: String? = null
)
