package com.example.awesomearchsample.data.repo.remote.network

import com.example.awesomearchsample.data.repo.remote.network.model.RepoDetailsNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class RepoApi(private val httpClient: HttpClient) {

    suspend fun getRepos(): List<RepoNetModel> {
        return httpClient.get(urlString = "repositories")
            .body()
    }

    suspend fun getRepoDetails(repoId: Long): RepoDetailsNetModel {
        return httpClient.get(urlString = "repositories/$repoId")
            .body()
    }
}
