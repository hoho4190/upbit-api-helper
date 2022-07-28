package com.hoho.upbitapihelper.dto.board

import kotlinx.serialization.Serializable

/**
 * 업비트 게시판
 *
 * 공지사항, 업비트 소식, 프로젝트 공시
 */
@Serializable
data class Board<T: BoardData>(

    val success: Boolean,

    val data: T,

    val time: String? = null
)
