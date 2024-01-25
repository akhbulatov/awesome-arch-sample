package com.example.awesomearchsample.core.network

import com.example.awesomearchsample.feature.repo.data.network.RepoNetModel
import retrofit2.http.GET

interface GitHubApi {
    @GET("repositories")
    suspend fun getRepos(): List<RepoNetModel>
}