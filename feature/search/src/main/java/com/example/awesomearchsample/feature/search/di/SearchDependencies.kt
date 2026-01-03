package com.example.awesomearchsample.feature.search.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.search.usecase.GetSearchQueriesUseCase
import com.example.awesomearchsample.domain.search.usecase.GetSearchResultUseCase
import com.example.awesomearchsample.domain.search.usecase.SaveSearchQueryUseCase
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator

interface SearchDependencies {
    val searchNavigator: SearchNavigator
    val getSearchResultUseCase: GetSearchResultUseCase
    val getSearchQueriesUseCase: GetSearchQueriesUseCase
    val saveSearchQueryUseCase: SaveSearchQueryUseCase
    val uiErrorHandler: UiErrorHandler
}

@Composable
fun rememberSearchDependencies(): SearchDependencies {
    return rememberSearchFeatureDependencies().searchDependencies
}
