package com.example.awesomearchsample.feature.repo.internal.di

import com.example.awesomearchsample.feature.repo.internal.RepoRepositoryImpl
import com.example.awesomearchsample.feature.repo.internal.network.RepoApi
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepoDataModule {

    @Binds
    @Singleton
    abstract fun bindRepoRepository(impl: RepoRepositoryImpl): RepoRepository

    companion object {
        @Provides
        @Singleton
        fun provideRepoApi(httpClient: HttpClient): RepoApi = RepoApi(httpClient = httpClient)
    }
}