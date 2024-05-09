package com.example.awesomearchsample.feature.repo.internal.network

import retrofit2.http.GET
import retrofit2.http.Path

internal interface RepoApi {
    @GET("repositories")
    suspend fun getRepos(): List<RepoNetModel>

    @GET("repositories/{id}")
    suspend fun getRepoDetails(
        @Path("id") repoId: Long
    ): RepoDetailsNetModel
}