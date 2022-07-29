package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 입출금 현황 정보
 */
@Serializable
data class WalletStatus(

    /**
     * 화폐를 의미하는 영문 대문자 코드
     */
    val currency: String,

    /**
     * 입출금 상태
     */
    @SerialName("wallet_state")
    val walletState: WalletState,

    /**
     * 블록 상태
     */
    @SerialName("block_state")
    val blockState: BlockState,

    /**
     * 블록 높이
     */
    @SerialName("block_height")
    val blockHeight: Int? = null,

    /**
     * 블록 갱신 시각
     *
     * DateString
     */
    @SerialName("block_updated_at")
    val blockUpdatedAt: String,

    /**
     * FIXME 문서에 없음
     */
    @SerialName("block_elapsed_minutes")
    val blockElapsedMinutes: Int
) {

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

    @Serializable
    enum class BlockState {

        /**
         * 정상
         */
        @SerialName("normal")
        NORMAL,

        /**
         * 지연
         */
        @SerialName("delayed")
        DELAYED,

        /**
         * 비활성 (점검 등)
         */
        @SerialName("inactive")
        INACTIVE
    }
}