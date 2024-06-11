package com.example.awesomearchsample.domain.search.usecase

import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import javax.inject.Inject

class SaveSearchQueryUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: SearchQuery) {
        searchRepository.saveSearchQuery(query)
    }
}