package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 주문 상태
 */
@Serializable
enum class OrderState {

    /**
     * 체결 대기 (default)
     */
    @SerialName("wait")
    WAIT,

    /**
     * 예약주문 대기
     */
    @SerialName("watch")
    WATCH,

    /**
     * 전체 체결 완료
     */
    @SerialName("done")
    DONE,

    /**
     * 주문 취소
     */
    @SerialName("cancel")
    CANCEL
}