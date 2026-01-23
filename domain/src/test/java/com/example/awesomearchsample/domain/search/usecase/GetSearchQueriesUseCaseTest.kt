package com.example.awesomearchsample.domain.search.usecase

import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSearchQueriesUseCaseTest {

    @Test
    fun invoke_returnsSearchQueries() = runBlocking {
        // Arrange
        val expected = listOf(
            SearchQuery("awesome arch sample"),
            SearchQuery("compose")
        )
        val repository = FakeSearchRepository(flowOf(expected))
        val useCase = GetSearchQueriesUseCase(repository)

        // Act
        val actual = useCase.invoke().first()

        // Assert
        assertEquals(expected, actual)
    }

    private class FakeSearchRepository(
        private val queriesFlow: Flow<List<SearchQuery>>
    ) : SearchRepository {
        override suspend fun getSearchResult(query: String): SearchResult {
            error("Not used in this test")
        }

        override fun getSearchQueries(): Flow<List<SearchQuery>> = queriesFlow

        override suspend fun saveSearchQuery(query: SearchQuery) {
            error("Not used in this test")
        }
    }
}
