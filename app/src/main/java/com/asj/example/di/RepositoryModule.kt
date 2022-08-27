package com.asj.example.di

import com.asj.example.core.GithubRepository
import com.asj.example.data.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun githubRepository(impl: GithubRepositoryImpl): GithubRepository
}