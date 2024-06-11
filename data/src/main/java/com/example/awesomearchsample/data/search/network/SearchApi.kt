package com.example.awesomearchsample.data.search.network

import com.example.awesomearchsample.data.repo.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.network.model.SearchResultNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class SearchApi(private val httpClient: HttpClient) {

    suspend fun getReposSearchResult(query: String): SearchResultNetModel<List<RepoNetModel>> {
        return httpClient.get(urlString = "search/repositories?q=$query")
            .body()
    }
}