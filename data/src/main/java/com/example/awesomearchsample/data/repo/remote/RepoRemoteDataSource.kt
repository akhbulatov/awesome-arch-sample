package com.example.awesomearchsample.data.repo.remote

import com.example.awesomearchsample.data.repo.network.RepoApi
import com.example.awesomearchsample.data.repo.network.mapRepoDetailsFromNet
import com.example.awesomearchsample.data.repo.network.mapRepoFromNet
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails

internal class RepoRemoteDataSource(
    private val repoApi: RepoApi
) {

    suspend fun getRepos(): List<Repo> {
        return repoApi.getRepos()
            .map { repoNetModel ->
                repoNetModel.mapRepoFromNet()
            }
    }

    suspend fun getRepoDetails(repoId: Long): RepoDetails {
        return repoApi.getRepoDetails(repoId)
            .mapRepoDetailsFromNet()
    }
}
