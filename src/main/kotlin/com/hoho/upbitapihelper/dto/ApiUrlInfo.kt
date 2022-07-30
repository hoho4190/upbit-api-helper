package com.hoho.upbitapihelper.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * API url info
 */
@Serializable
data class ApiUrlInfo(

    @SerialName("api-manager")
    var apiManager: String,

    @SerialName("project-team")
    var projectTeam: String ,

    var api: String
)