package com.example.awesomearchsample.feature.repo.internal.di

import com.example.awesomearchsample.feature.repo.internal.RepoRepositoryImpl
import com.example.awesomearchsample.feature.repo.internal.network.RepoApi
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
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
        fun provideRepoApi(retrofit: Retrofit): RepoApi = retrofit.create()
    }
}