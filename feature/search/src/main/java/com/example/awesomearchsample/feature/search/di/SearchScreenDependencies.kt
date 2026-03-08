package com.example.awesomearchsample.feature.search.di

import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.search.usecase.GetSearchQueriesUseCase
import com.example.awesomearchsample.domain.search.usecase.GetSearchResultUseCase
import com.example.awesomearchsample.domain.search.usecase.SaveSearchQueryUseCase

interface SearchScreenDependencies {
    val getSearchResultUseCase: GetSearchResultUseCase
    val getSearchQueriesUseCase: GetSearchQueriesUseCase
    val saveSearchQueryUseCase: SaveSearchQueryUseCase
    val uiErrorHandler: UiErrorHandler
}
