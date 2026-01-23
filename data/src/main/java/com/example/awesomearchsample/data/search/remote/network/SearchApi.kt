package com.example.awesomearchsample.data.search.remote.network

import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.remote.network.model.SearchResultNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal interface SearchApi {
    suspend fun getReposSearchResult(query: String): SearchResultNetModel<List<RepoNetModel>>
}

internal class KtorSearchApi(
    private val httpClient: HttpClient
) : SearchApi {

    override suspend fun getReposSearchResult(query: String): SearchResultNetModel<List<RepoNetModel>> {
        return httpClient.get(urlString = "search/repositories?q=$query")
            .body()
    }
}
