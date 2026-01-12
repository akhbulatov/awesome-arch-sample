package com.example.awesomearchsample.data.search.remote

import com.example.awesomearchsample.data.search.remote.network.SearchApi
import com.example.awesomearchsample.data.search.remote.network.toSearchResultReposDomain
import com.example.awesomearchsample.domain.search.model.SearchResult

internal class SearchRemoteDataSource(
    private val searchApi: SearchApi
) {

    suspend fun getSearchResult(query: String): SearchResult {
        return searchApi.getReposSearchResult(query)
            .toSearchResultReposDomain()
    }
}
