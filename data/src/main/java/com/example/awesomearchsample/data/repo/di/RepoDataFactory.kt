package com.example.awesomearchsample.data.repo.di

import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.data.repo.RepoRepositoryImpl
import com.example.awesomearchsample.data.repo.remote.network.RepoApi
import com.example.awesomearchsample.data.repo.remote.RepoRemoteDataSource
import com.example.awesomearchsample.domain.repo.repository.RepoRepository

class RepoDataFactory(
    private val coreFactory: CoreFactory
) {

    private val repoApi: RepoApi by lazy {
        RepoApi(
            httpClient = coreFactory.networkFactory.httpClient
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
