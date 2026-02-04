package com.example.awesomearchsample.data.user.remote.network

import com.example.awesomearchsample.data.user.remote.network.model.UserDetailsNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal interface UserApi {
    suspend fun getUserDetails(login: String): UserDetailsNetModel
}

internal class KtorUserApi(
    private val httpClient: HttpClient
) : UserApi {

    override suspend fun getUserDetails(login: String): UserDetailsNetModel {
        return httpClient.get(urlString = "users/$login")
            .body()
    }
}
