package com.example.awesomearchsample.data.repo.di

import com.example.awesomearchsample.data.repo.RepoRepositoryImpl
import com.example.awesomearchsample.data.repo.network.RepoApi
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