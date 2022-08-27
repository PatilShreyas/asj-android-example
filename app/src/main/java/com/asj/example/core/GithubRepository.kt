package com.asj.example.core

interface GithubRepository {
    suspend fun findUser(username: String): GithubUser?
    suspend fun getUserRepos(username: String): List<GithubRepo>
}