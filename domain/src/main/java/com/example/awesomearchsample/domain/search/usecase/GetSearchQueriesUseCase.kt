package com.example.awesomearchsample.domain.search.usecase

import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchQueriesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(): Flow<List<SearchQuery>> {
        return searchRepository.getSearchQueries()
    }
}