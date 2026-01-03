package com.example.awesomearchsample.data.search.remote

import com.example.awesomearchsample.data.search.network.SearchApi
import com.example.awesomearchsample.data.search.network.mapReposToDomainModel
import com.example.awesomearchsample.domain.search.model.SearchResult

internal class SearchRemoteDataSource(
    private val searchApi: SearchApi
) {

    suspend fun getSearchResult(query: String): SearchResult {
        return searchApi.getReposSearchResult(query)
            .mapReposToDomainModel()
    }
}
