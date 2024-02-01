package com.example.awesomearchsample.feature.repo.data

import com.example.awesomearchsample.core.network.GitHubApi
import com.example.awesomearchsample.feature.repo.data.network.RepoNetworkMapper
import com.example.awesomearchsample.domain.model.Repo
import com.example.awesomearchsample.feature.repo.domain.repository.RepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val repoNetworkMapper: RepoNetworkMapper
) : RepoRepository {

    override suspend fun getRepos(): List<com.example.awesomearchsample.domain.model.Repo> {
        return gitHubApi.getRepos()
            .map { repoNetModel ->
                repoNetworkMapper.mapRepoFromNet(repoNetModel)
            }
    }
}