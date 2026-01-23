package com.example.awesomearchsample.data.search.remote

import com.example.awesomearchsample.data.repo.remote.network.model.OwnerNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.remote.network.SearchApi
import com.example.awesomearchsample.data.search.remote.network.model.SearchResultNetModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchRemoteDataSourceTest {

    @Test
    fun getSearchResult_returnsMappedSearchResult() = runBlocking {
        // Arrange
        val query = "awesome"
        val netModel = SearchResultNetModel(
            items = listOf(
                RepoNetModel(
                    id = 1L,
                    name = "Awesome",
                    owner = OwnerNetModel(login = "Ada"),
                    description = "Sample"
                )
            )
        )
        val expected = SearchResult.Repos(
            data = listOf(
                Repo(
                    id = 1L,
                    name = "Awesome",
                    author = "Ada",
                    description = "Sample",
                    inFavorites = false
                )
            )
        )
        val api = FakeSearchApi(netModel)
        val dataSource = SearchRemoteDataSource(api)

        // Act
        val actual = dataSource.getSearchResult(query)

        // Assert
        assertEquals(expected, actual)
        assertEquals(query, api.lastRequestedQuery)
    }

    private class FakeSearchApi(
        private val searchResult: SearchResultNetModel<List<RepoNetModel>>
    ) : SearchApi {
        var lastRequestedQuery: String? = null
            private set

        override suspend fun getReposSearchResult(query: String): SearchResultNetModel<List<RepoNetModel>> {
            lastRequestedQuery = query
            return searchResult
        }
    }
}
