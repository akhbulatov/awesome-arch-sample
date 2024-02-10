package com.example.awesomearchsample.feature.repo.internal.network

import retrofit2.http.GET

internal interface RepoApi {
    @GET("repositories")
    suspend fun getRepos(): List<RepoNetModel>
}