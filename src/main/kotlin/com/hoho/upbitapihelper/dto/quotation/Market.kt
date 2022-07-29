package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 마켓
 */
@Serializable
data class Market(

    /**
     * 업비트에서 제공중인 시장 정보
     */
    val market: String,

    /**
     * 거래 대상 암호화폐 한글명
     */
    @SerialName("korean_name")
    val koreanName: String,

    /**
     * 거래 대상 암호화폐 영문명
     */
    @SerialName("english_name")
    val englishName: String,

    /**
     * 유의 종목 여부
     * NONE (해당 사항 없음), CAUTION(투자유의)
     */
    @SerialName("market_warning")
    val marketWarning: MarketWarning? = null
)
