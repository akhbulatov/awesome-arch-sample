package com.example.awesomearchsample.domain.search.repository

import com.example.awesomearchsample.domain.search.model.SearchResult

interface SearchRepository {
    suspend fun getSearchResult(query: String): SearchResult
}