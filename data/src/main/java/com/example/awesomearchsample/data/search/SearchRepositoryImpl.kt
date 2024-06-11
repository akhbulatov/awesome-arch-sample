package com.example.awesomearchsample.data.search

import com.example.awesomearchsample.data.search.network.SearchApi
import com.example.awesomearchsample.data.search.network.mapReposToDomainModel
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
) : SearchRepository {

    override suspend fun getSearchResult(query: String): SearchResult {
        return searchApi.getReposSearchResult(query)
            .mapReposToDomainModel()
    }
}