package com.example.awesomearchsample.feature.search

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult

sealed class SearchUiState {
    data class Idle(val recentQueries: List<SearchQuery> = emptyList()) : SearchUiState()
    data object Loading : SearchUiState()
    data class Error(val error: UiError) : SearchUiState()
    data class Content(val result: SearchResult) : SearchUiState()
}
