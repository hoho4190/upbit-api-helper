package com.hoho.upbitapihelper.dto.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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