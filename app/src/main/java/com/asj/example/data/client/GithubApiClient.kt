package com.asj.example.data.client

import com.asj.example.data.model.GithubUserRepoResponse
import com.asj.example.data.model.GithubUserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Inject
import kotlinx.serialization.json.Json

class GithubApiClient @Inject constructor(private val httpClient: HttpClient) {
    suspend fun findUser(username: String): GithubUserResponse {
        return httpClient.get(urlString = "https://api.github.com/users/$username").body()
    }

    suspend fun getUserRepos(username: String): List<GithubUserRepoResponse> {
        return httpClient.get(urlString = "https://api.github.com/users/$username/repos").body()
    }
}