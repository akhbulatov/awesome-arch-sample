package com.example.awesomearchsample.feature.user.internal.di

import com.example.awesomearchsample.domain.user.repository.UserRepository
import com.example.awesomearchsample.feature.user.internal.UserRepositoryImpl
import com.example.awesomearchsample.feature.user.internal.network.UserApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserDataModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserApi(httpClient: HttpClient): UserApi = UserApi(httpClient = httpClient)
    }
}