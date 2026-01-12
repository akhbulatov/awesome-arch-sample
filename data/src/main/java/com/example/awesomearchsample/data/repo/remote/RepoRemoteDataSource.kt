package com.example.awesomearchsample.data.repo.remote

import com.example.awesomearchsample.data.repo.remote.network.RepoApi
import com.example.awesomearchsample.data.repo.remote.network.toRepoDetailsDomain
import com.example.awesomearchsample.data.repo.remote.network.toRepoDomain
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails

internal class RepoRemoteDataSource(
    private val repoApi: RepoApi
) {

    suspend fun getRepos(): List<Repo> {
        return repoApi.getRepos()
            .map { repoNetModel ->
                repoNetModel.toRepoDomain()
            }
    }

    suspend fun getRepoDetails(repoId: Long): RepoDetails {
        return repoApi.getRepoDetails(repoId)
            .toRepoDetailsDomain()
    }
}
