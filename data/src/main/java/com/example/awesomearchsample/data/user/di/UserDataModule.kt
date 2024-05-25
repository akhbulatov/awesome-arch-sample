package com.example.awesomearchsample.data.user.di

import com.example.awesomearchsample.data.user.UserRepositoryImpl
import com.example.awesomearchsample.data.user.network.UserApi
import com.example.awesomearchsample.domain.user.repository.UserRepository
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