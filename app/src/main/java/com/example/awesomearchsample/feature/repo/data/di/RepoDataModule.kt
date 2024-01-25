package com.example.awesomearchsample.feature.repo.data.di

import com.example.awesomearchsample.feature.repo.data.RepoRepositoryImpl
import com.example.awesomearchsample.feature.repo.domain.repository.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoDataModule {

    @Binds
    @Singleton
    abstract fun bindRepoRepository(impl: RepoRepositoryImpl): RepoRepository
}