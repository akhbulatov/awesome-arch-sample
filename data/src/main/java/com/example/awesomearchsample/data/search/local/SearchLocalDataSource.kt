package com.example.awesomearchsample.data.search.local

import com.example.awesomearchsample.data.search.database.SearchQueryDao
import com.example.awesomearchsample.data.search.database.toDbModel
import com.example.awesomearchsample.data.search.database.toDomainModel
import com.example.awesomearchsample.domain.search.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SearchLocalDataSource(
    private val searchQueryDao: SearchQueryDao
) {

    fun getSearchQueries(): Flow<List<SearchQuery>> {
        return searchQueryDao.getAll()
            .map { queryDbList ->
                queryDbList.map { queryDb -> queryDb.toDomainModel() }
            }
    }

    suspend fun saveSearchQuery(query: SearchQuery) {
        searchQueryDao.insert(query.toDbModel())
    }
}
