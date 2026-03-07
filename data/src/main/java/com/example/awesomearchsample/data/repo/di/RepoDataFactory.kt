package com.example.awesomearchsample.data.repo.di

import com.example.awesomearchsample.data.repo.RepoRepositoryImpl
import com.example.awesomearchsample.data.repo.remote.network.KtorRepoApi
import com.example.awesomearchsample.data.repo.remote.network.RepoApi
import com.example.awesomearchsample.data.repo.remote.RepoRemoteDataSource
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.core.network.di.NetworkFactory

class RepoDataFactory(
    private val networkFactory: NetworkFactory
) {

    private val repoApi: RepoApi by lazy {
        KtorRepoApi(
            httpClient = networkFactory.httpClient
        )
    }

    val repoRepository: RepoRepository by lazy {
        RepoRepositoryImpl(
            remoteDataSource = RepoRemoteDataSource(
                repoApi = repoApi
            )
        )
    }
}
