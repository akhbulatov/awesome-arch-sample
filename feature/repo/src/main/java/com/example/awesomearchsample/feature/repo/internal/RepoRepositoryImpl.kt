package com.example.awesomearchsample.feature.repo.internal

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.feature.repo.internal.network.RepoApi
import com.example.awesomearchsample.feature.repo.internal.network.mapRepoDetailsFromNet
import com.example.awesomearchsample.feature.repo.internal.network.mapRepoFromNet
import javax.inject.Inject

internal class RepoRepositoryImpl @Inject constructor(
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