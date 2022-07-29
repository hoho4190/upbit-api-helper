package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 체결 내역
 */
@Serializable
data class Tick(

    /**
     * 마켓 구분 코드
     */
    val market: String,

    /**
     * 체결 일자(UTC 기준)
     */
    @SerialName("trade_date_utc")
    val tradeDateUtc: String,

    /**
     * 체결 시각(UTC 기준)
     */
    @SerialName("trade_time_utc")
    val tradeTimeUtc: String,

    /**
     * 체결 타임스탬프
     */
    val timestamp: Long,

    /**
     * 체결 가격
     */
    @SerialName("trade_price")
    val tradePrice: Double,

    /**
     * 체결량
     */
    @SerialName("trade_volume")
    val tradeVolume: Double,

    /**
     * 전일 종가(UTC 0시 기준)
     */
    @SerialName("prev_closing_price")
    val prevClosingPrice: Double,

    /**
     * 변화량
     */
    @SerialName("change_price")
    val changePrice: Double,

    /**
     * 매도/매수
     * 
     * ask: 매도, bid: 매수
     */
    @SerialName("ask_bid")
    val askBid: TickOrderSide,

    /**
     * 체결 번호(Unique)
     *
     * 체결의 유일성 판단을 위한 근거로 쓰일 수 있습니다.
     * 하지만 체결의 순서를 보장하지는 못합니다.
     */
    @SerialName("sequential_id")
    val sequentialId: Long
)
