package com.example.awesomearchsample.data.repo.di

import com.example.awesomearchsample.data.repo.RepoRepositoryImpl
import com.example.awesomearchsample.data.repo.remote.network.KtorRepoApi
import com.example.awesomearchsample.data.repo.remote.network.RepoApi
import com.example.awesomearchsample.data.repo.remote.RepoRemoteDataSource
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import io.ktor.client.HttpClient

class RepoDataFactory(
    httpClientProvider: () -> HttpClient
) {

    private val repoApi: RepoApi by lazy {
        KtorRepoApi(
            httpClient = httpClientProvider()
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
