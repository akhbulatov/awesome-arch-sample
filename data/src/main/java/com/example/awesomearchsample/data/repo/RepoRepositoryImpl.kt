package com.example.awesomearchsample.data.repo

import com.example.awesomearchsample.data.repo.network.RepoApi
import com.example.awesomearchsample.data.repo.network.mapRepoDetailsFromNet
import com.example.awesomearchsample.data.repo.network.mapRepoFromNet
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository

internal class RepoRepositoryImpl(
    private val repoApi: RepoApi,
) : RepoRepository {

    override suspend fun getRepos(): List<Repo> {
        return repoApi.getRepos()
            .map { repoNetModel ->
                repoNetModel.mapRepoFromNet()
            }
    }

    override suspend fun getRepoDetails(repoId: Long): RepoDetails {
        return repoApi.getRepoDetails(repoId)
            .mapRepoDetailsFromNet()
    }
}