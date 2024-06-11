package com.example.awesomearchsample.data.search

import com.example.awesomearchsample.data.search.database.SearchQueryDao
import com.example.awesomearchsample.data.search.database.toDbModel
import com.example.awesomearchsample.data.search.database.toDomainModel
import com.example.awesomearchsample.data.search.network.SearchApi
import com.example.awesomearchsample.data.search.network.mapReposToDomainModel
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val searchQueryDao: SearchQueryDao
) : SearchRepository {

    override suspend fun getSearchResult(query: String): SearchResult {
        return searchApi.getReposSearchResult(query)
            .mapReposToDomainModel()
    }

    override fun getSearchQueries(): Flow<List<SearchQuery>> {
        return searchQueryDao.getAll()
            .map { queryDbList ->
                queryDbList.map { queryDb -> queryDb.toDomainModel() }
            }
    }

    override suspend fun saveSearchQuery(query: SearchQuery) {
        searchQueryDao.insert(query.toDbModel())
    }
}