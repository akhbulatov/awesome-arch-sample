package com.example.awesomearchsample.data.user.di

import com.example.awesomearchsample.data.user.UserRepositoryImpl
import com.example.awesomearchsample.data.user.remote.network.UserApi
import com.example.awesomearchsample.data.user.remote.UserRemoteDataSource
import com.example.awesomearchsample.data.user.remote.network.KtorUserApi
import com.example.awesomearchsample.domain.user.repository.UserRepository
import io.ktor.client.HttpClient

class UserDataFactory(
    httpClientProvider: () -> HttpClient
) {

    private val userApi: UserApi by lazy {
        KtorUserApi(
            httpClient = httpClientProvider()
        )
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            remoteDataSource = UserRemoteDataSource(
                userApi = userApi
            )
        )
    }
}
