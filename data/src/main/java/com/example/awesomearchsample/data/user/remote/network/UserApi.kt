package com.example.awesomearchsample.data.user.remote.network

import com.example.awesomearchsample.data.user.remote.network.model.UserDetailsNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class UserApi(private val httpClient: HttpClient) {

    suspend fun getUserDetails(login: String): UserDetailsNetModel {
        return httpClient.get(urlString = "users/$login")
            .body()
    }
}
