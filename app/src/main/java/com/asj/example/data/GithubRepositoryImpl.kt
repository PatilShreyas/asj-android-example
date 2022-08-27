package com.asj.example.data

import com.asj.example.core.GithubRepo
import com.asj.example.core.GithubRepository
import com.asj.example.core.GithubUser
import com.asj.example.data.client.GithubApiClient

class GithubRepositoryImpl(
    private val githubApiClient: GithubApiClient
): GithubRepository {
    override suspend fun findUser(username: String): GithubUser? {
        try {
            val response = githubApiClient.findUser(username)
            return GithubUser(
                username = response.login,
                profileUrl = response.avatarUrl,
                type = response.type,
                name = response.name,
                companyName = response.companyName,
                location = response.location,
                bio = response.bio,
                reposCount = response.reposCount,
                followers = response.followers
            )
        } catch (error: Throwable) {
            return null
        }
    }

    override suspend fun getUserRepos(username: String): List<GithubRepo> {
        try {
             return githubApiClient.getUserRepos(username).map { response ->
                GithubRepo(
                    name = response.name,
                    description = response.description,
                    stars = response.stars,
                    watchers = response.watchers
                )
            }
        } catch (error: Throwable) {
            return emptyList()
        }
    }
}