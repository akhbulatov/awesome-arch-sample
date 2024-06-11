package com.example.awesomearchsample.data.search.di

import com.example.awesomearchsample.data.search.SearchRepositoryImpl
import com.example.awesomearchsample.data.search.network.SearchApi
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SearchDataModule {

    @Binds
    @Singleton
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    companion object {
        @Provides
        @Singleton
        fun provideSearchApi(httpClient: HttpClient): SearchApi = SearchApi(httpClient = httpClient)
    }
}