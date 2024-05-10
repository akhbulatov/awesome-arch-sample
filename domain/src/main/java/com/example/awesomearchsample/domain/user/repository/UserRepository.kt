package com.example.awesomearchsample.domain.user.repository

import com.example.awesomearchsample.domain.user.model.UserDetails

interface UserRepository {
    suspend fun getUserDetails(login: String): UserDetails
}