package com.example.awesomearchsample.data.search

import com.example.awesomearchsample.data.search.local.SearchLocalDataSource
import com.example.awesomearchsample.data.search.remote.SearchRemoteDataSource
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

internal class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource,
    private val localDataSource: SearchLocalDataSource
) : SearchRepository {

    override suspend fun getSearchResult(query: String): SearchResult {
        return remoteDataSource.getSearchResult(query)
    }

    override fun getSearchQueries(): Flow<List<SearchQuery>> {
        return localDataSource.getSearchQueries()
    }

    override suspend fun saveSearchQuery(query: SearchQuery) {
        localDataSource.saveSearchQuery(query)
    }
}
