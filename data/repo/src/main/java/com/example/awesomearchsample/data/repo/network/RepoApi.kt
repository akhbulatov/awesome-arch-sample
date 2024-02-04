package com.example.awesomearchsample.data.repo.network

import retrofit2.http.GET

interface RepoApi {
    @GET("repositories")
    suspend fun getRepos(): List<RepoNetModel>
}