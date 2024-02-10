package com.example.awesomearchsample.feature.repo.internal

import com.example.awesomearchsample.feature.repo.internal.network.RepoApi
import com.example.awesomearchsample.feature.repo.internal.network.RepoNetworkMapper
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.model.Repo
import javax.inject.Inject

internal class RepoRepositoryImpl @Inject constructor(
    private val repoApi: RepoApi,
    private val repoNetworkMapper: RepoNetworkMapper
) : RepoRepository {

    override suspend fun getRepos(): List<Repo> {
        return repoApi.getRepos()
            .map { repoNetModel ->
                repoNetworkMapper.mapRepoFromNet(repoNetModel)
            }
    }
}