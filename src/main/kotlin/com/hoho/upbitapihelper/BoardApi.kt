package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.board.*
import com.hoho.upbitapihelper.service.ApiManagerService
import com.hoho.upbitapihelper.service.ProjectTeamService
import retrofit2.Call

/**
 * 업비트 게시판 API
 *
 * 공지사항, 업비트 소식, 프로젝트 공시
 */
object BoardApi {

    private val amService = ApiManagerService.instance
    private val ptService = ProjectTeamService.instance

    /**
     * 공지사항 조회
     *
     * @param page
     * @param perPage 기본 값: 20
     */
    @JvmStatic
    @JvmOverloads
    fun getNoticeList(
        page: Int,
        perPage: Int = 20
    ): Call<Board<NoticeData>> = amService.getNotices(
        page,
        perPage,
        BoardThreadName.GENERAL.name
    )

    /**
     * 업비트 소식 조회
     *
     * @param page
     * @param perPage 기본 값: 20
     */
    @JvmStatic
    @JvmOverloads
    fun getNewsList(
        page: Int,
        perPage: Int = 20
    ): Call<Board<NewsData>> = amService.getNews(
        page,
        perPage,
        BoardThreadName.PRESS.name
    )

    /**
     * 프로젝트 공시 조회
     *
     * offset 아래로(미만) perPage 만큼 조회
     *
     * @param perPage
     * @param offset 조회의 기준이 되는 공시 아이디. 기본 값: null
     * @param region 기본 값: kr
     */
    @JvmStatic
    @JvmOverloads
    fun getDisclosureList(
        perPage: Int,
        offset: Int? = null,
        region: String = "kr"
    ): Call<Board<DisclosureData>> = ptService.getDisclosures(
        perPage,
        offset,
        region
    )
}