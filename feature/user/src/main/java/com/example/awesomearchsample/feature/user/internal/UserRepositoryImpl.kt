package com.example.awesomearchsample.feature.user.internal

import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository
import com.example.awesomearchsample.feature.user.internal.network.UserApi
import com.example.awesomearchsample.feature.user.internal.network.mapUserDetailsFromNet
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUserDetails(login: String): UserDetails {
        return userApi.getUserDetails(login)
            .mapUserDetailsFromNet()
    }
}