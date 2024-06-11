package com.example.awesomearchsample.feature.search

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult

data class SearchUiState(
    val emptyProgress: Boolean = false,
    val emptyError: UiError? = null,
    val result: SearchResult? = null,
    val recentQueries: List<SearchQuery> = emptyList()
)