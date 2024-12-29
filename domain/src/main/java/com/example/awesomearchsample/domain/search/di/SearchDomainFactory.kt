package com.example.awesomearchsample.domain.search.di

import com.example.awesomearchsample.domain.search.repository.SearchRepository
import com.example.awesomearchsample.domain.search.usecase.GetSearchQueriesUseCase
import com.example.awesomearchsample.domain.search.usecase.GetSearchResultUseCase
import com.example.awesomearchsample.domain.search.usecase.SaveSearchQueryUseCase

class SearchDomainFactory(
    searchRepository: SearchRepository
) {

    val getSearchQueriesUseCase: GetSearchQueriesUseCase = GetSearchQueriesUseCase(
        searchRepository = searchRepository
    )
    val saveSearchQueriesUseCase: SaveSearchQueryUseCase = SaveSearchQueryUseCase(
        searchRepository = searchRepository
    )


    val getSearchResultUseCase: GetSearchResultUseCase = GetSearchResultUseCase(
        searchRepository = searchRepository
    )
}