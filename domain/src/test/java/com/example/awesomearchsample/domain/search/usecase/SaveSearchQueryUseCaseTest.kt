package com.example.awesomearchsample.domain.search.usecase

import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SaveSearchQueryUseCaseTest {

    @Test
    fun invoke_savesSearchQuery() = runBlocking {
        // Arrange
        val query = SearchQuery("awesome arch sample")
        val repository = FakeSearchRepository()
        val useCase = SaveSearchQueryUseCase(repository)

        // Act
        useCase.invoke(query)

        // Assert
        assertEquals(query, repository.lastSavedQuery)
    }

    private class FakeSearchRepository : SearchRepository {
        var lastSavedQuery: SearchQuery? = null
            private set

        override suspend fun getSearchResult(query: String): SearchResult {
            error("Not used in this test")
        }

        override fun getSearchQueries(): Flow<List<SearchQuery>> {
            error("Not used in this test")
        }

        override suspend fun saveSearchQuery(query: SearchQuery) {
            lastSavedQuery = query
        }
    }
}
