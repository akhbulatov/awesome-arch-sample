package com.example.awesomearchsample.data.repo

import com.example.awesomearchsample.data.repo.network.RepoApi
import com.example.awesomearchsample.data.repo.network.RepoNetworkMapper
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.model.Repo
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
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