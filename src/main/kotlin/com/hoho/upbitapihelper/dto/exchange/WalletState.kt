package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WalletState {

    /**
     * 입출금 가능
     */
    @SerialName("working")
    WORKING,

    /**
     * 출금만 가능
     */
    @SerialName("withdraw_only")
    WITHDRAW_ONLY,

    /**
     * 입금만 가능
     */
    @SerialName("deposit_only")
    DEPOSIT_ONLY,

    /**
     * 입출금 중단
     */
    @SerialName("paused")
    PAUSED,

    /**
     * 입출금 미지원
     */
    @SerialName("unsupported")
    UNSUPPORTED
}