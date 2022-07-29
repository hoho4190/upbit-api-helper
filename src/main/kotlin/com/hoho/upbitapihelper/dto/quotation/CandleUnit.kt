package com.hoho.upbitapihelper.dto.quotation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 분 단위. 가능한 값 : 1, 3, 5, 15, 10, 30, 60, 240
 */
@Serializable
enum class CandleUnit(val value: Int) {

    @SerialName("1")
    UNIT_1(1),

    @SerialName("3")
    UNIT_3(3),

    @SerialName("5")
    UNIT_5(5),

    @SerialName("15")
    UNIT_15(15),

    @SerialName("10")
    UNIT_10(10),

    @SerialName("30")
    UNIT_30(30),

    @SerialName("60")
    UNIT_60(60),

    @SerialName("240")
    UNIT_240(240)
}