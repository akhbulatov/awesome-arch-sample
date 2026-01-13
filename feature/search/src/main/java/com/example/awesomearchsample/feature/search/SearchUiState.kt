package com.example.awesomearchsample.feature.search

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.mvvm.SuccessState
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult

internal sealed class SearchUiState {
    data class Idle(val recentQueries: List<SearchQuery> = emptyList()) : SearchUiState()
    data object Loading : SearchUiState()
    data class Error(val error: UiError) : SearchUiState()
    data class Success(val result: SearchResult) : SearchUiState(), SuccessState<SearchResult> {
        override val data: SearchResult
            get() = result
    }
}
