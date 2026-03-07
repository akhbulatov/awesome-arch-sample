package com.example.awesomearchsample.data.user.di

import com.example.awesomearchsample.core.network.di.NetworkFactory
import com.example.awesomearchsample.data.user.UserRepositoryImpl
import com.example.awesomearchsample.data.user.remote.network.UserApi
import com.example.awesomearchsample.data.user.remote.UserRemoteDataSource
import com.example.awesomearchsample.data.user.remote.network.KtorUserApi
import com.example.awesomearchsample.domain.user.repository.UserRepository

class UserDataFactory(
    private val networkFactory: NetworkFactory
) {

    private val userApi: UserApi by lazy {
        KtorUserApi(
            httpClient = networkFactory.httpClient
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
