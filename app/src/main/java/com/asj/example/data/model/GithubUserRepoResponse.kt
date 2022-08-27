package com.asj.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserRepoResponse(
    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String?,

    @SerialName("stargazers_count")
    val stars: Int,

    @SerialName("watchers_count")
    val watchers: Int
)
