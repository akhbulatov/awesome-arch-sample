package com.example.awesomearchsample.domain.search.repository

import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchResult(query: String): SearchResult

    fun getSearchQueries(): Flow<List<SearchQuery>>
    suspend fun saveSearchQuery(query: SearchQuery)
}