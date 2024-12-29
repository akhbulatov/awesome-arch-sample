package com.example.awesomearchsample.data.user.di

import com.example.awesomearchsample.core.commonfactory.di.CoreFactory
import com.example.awesomearchsample.data.user.UserRepositoryImpl
import com.example.awesomearchsample.data.user.network.UserApi
import com.example.awesomearchsample.domain.user.repository.UserRepository

class UserDataFactory(
    private val coreFactory: CoreFactory
) {

    private val userApi: UserApi by lazy {
        UserApi(
            httpClient = coreFactory.networkFactory.httpClient
        )
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            userApi = userApi
        )
    }
}