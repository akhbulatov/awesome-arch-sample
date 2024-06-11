package com.example.awesomearchsample.domain.search.usecase

import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String): SearchResult {
        return searchRepository.getSearchResult(query)
    }
}