package com.example.awesomearchsample.data.user

import com.example.awesomearchsample.data.user.network.UserApi
import com.example.awesomearchsample.data.user.network.mapUserDetailsFromNet
import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository

internal class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUserDetails(login: String): UserDetails {
        return userApi.getUserDetails(login)
            .mapUserDetailsFromNet()
    }
}