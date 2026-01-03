package com.example.awesomearchsample.data.repo

import com.example.awesomearchsample.data.repo.remote.RepoRemoteDataSource
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository

internal class RepoRepositoryImpl(
    private val remoteDataSource: RepoRemoteDataSource,
) : RepoRepository {

    override suspend fun getRepos(): List<Repo> {
        return remoteDataSource.getRepos()
    }

    override suspend fun getRepoDetails(repoId: Long): RepoDetails {
        return remoteDataSource.getRepoDetails(repoId)
    }
}
