package com.hoho.upbitapihelper.dto.board

import kotlinx.serialization.Serializable

/**
 * 프로젝트 공시 데이터
 */
@Serializable
data class DisclosureData(

    val more: Boolean,

    /**
     * 공시 목록 중 마지막 공시 ID
     */
    val offset: Long,

    val posts: List<Disclosure>
) : BoardData()
