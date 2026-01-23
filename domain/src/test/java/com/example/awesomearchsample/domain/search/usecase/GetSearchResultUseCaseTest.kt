package com.example.awesomearchsample.domain.search.usecase

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSearchResultUseCaseTest {

    @Test
    fun invoke_returnsSearchResult() = runBlocking {
        // Arrange
        val query = "awesome arch sample"
        val expected = SearchResult.Repos(
            data = List(3) { index ->
                Repo(
                    id = index.toLong(),
                    name = "Awesome $index",
                    author = "Ada",
                    description = "Sample",
                    inFavorites = false
                )
            }
        )
        val repository = FakeSearchRepository(expected)
        val useCase = GetSearchResultUseCase(repository)

        // Act
        val actual = useCase.invoke(query)

        // Assert
        assertEquals(expected, actual)
        assertEquals(query, repository.lastRequestedQuery)
    }

    private class FakeSearchRepository(
        private val searchResult: SearchResult
    ) : SearchRepository {
        var lastRequestedQuery: String? = null
            private set

        override suspend fun getSearchResult(query: String): SearchResult {
            lastRequestedQuery = query
            return searchResult
        }

        override fun getSearchQueries(): Flow<List<SearchQuery>> {
            error("Not used in this test")
        }

        override suspend fun saveSearchQuery(query: SearchQuery) {
            error("Not used in this test")
        }
    }
}
