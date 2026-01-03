package com.example.awesomearchsample.data.user

import com.example.awesomearchsample.data.user.remote.UserRemoteDataSource
import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository

internal class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserDetails(login: String): UserDetails {
        return remoteDataSource.getUserDetails(login)
    }
}
