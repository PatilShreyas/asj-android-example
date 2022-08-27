package com.asj.example.core

interface GithubRepository {
    suspend fun findUser(username: String): GithubUser?
}