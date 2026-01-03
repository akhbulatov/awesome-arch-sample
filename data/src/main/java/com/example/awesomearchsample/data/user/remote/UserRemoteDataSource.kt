package com.example.awesomearchsample.data.user.remote

import com.example.awesomearchsample.data.user.network.UserApi
import com.example.awesomearchsample.data.user.network.mapUserDetailsFromNet
import com.example.awesomearchsample.domain.user.model.UserDetails

internal class UserRemoteDataSource(
    private val userApi: UserApi
) {

    suspend fun getUserDetails(login: String): UserDetails {
        return userApi.getUserDetails(login)
            .mapUserDetailsFromNet()
    }
}
