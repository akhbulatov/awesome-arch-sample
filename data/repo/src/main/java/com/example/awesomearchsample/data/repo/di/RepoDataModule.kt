package com.example.awesomearchsample.data.repo.di

import com.example.awesomearchsample.data.repo.RepoRepositoryImpl
import com.example.awesomearchsample.data.repo.network.RepoApi
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
abstract class RepoDataModule {

    @Binds
    @Singleton
    abstract fun bindRepoRepository(impl: RepoRepositoryImpl): RepoRepository

    companion object {
        @Provides
        @Singleton
        fun provideRepoApi(retrofit: Retrofit): RepoApi = retrofit.create()
    }
}