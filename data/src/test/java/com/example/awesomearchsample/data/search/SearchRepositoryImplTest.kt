package com.example.awesomearchsample.data.search

import com.example.awesomearchsample.data.repo.remote.network.model.OwnerNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.local.SearchLocalDataSource
import com.example.awesomearchsample.data.search.local.database.SearchQueryDao
import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import com.example.awesomearchsample.data.search.local.database.toSearchQueryDbModel
import com.example.awesomearchsample.data.search.remote.SearchRemoteDataSource
import com.example.awesomearchsample.data.search.remote.network.SearchApi
import com.example.awesomearchsample.data.search.remote.network.model.SearchResultNetModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchRepositoryImplTest {

    @Test
    fun getSearchResult_returnsRemoteSearchResult() = runBlocking {
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
        val remoteDataSource = SearchRemoteDataSource(api)
        val repository = SearchRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = SearchLocalDataSource(FakeSearchQueryDao())
        )

        // Act
        val actual = repository.getSearchResult(query)

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun getSearchQueries_returnsLocalQueries() = runBlocking {
        // Arrange
        val dbQueries = listOf(
            SearchQueryDbModel(value = "awesome"),
            SearchQueryDbModel(value = "compose")
        )
        val expected = listOf(
            SearchQuery(value = "awesome"),
            SearchQuery(value = "compose")
        )
        val dao = FakeSearchQueryDao(flowOf(dbQueries))
        val localDataSource = SearchLocalDataSource(dao)
        val repository = SearchRepositoryImpl(
            remoteDataSource = SearchRemoteDataSource(FakeSearchApi()),
            localDataSource = localDataSource
        )

        // Act
        val actual = repository.getSearchQueries().first()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun saveSearchQuery_insertsLocalQuery() = runBlocking {
        // Arrange
        val query = SearchQuery(value = "awesome arch")
        val expected = query.toSearchQueryDbModel()
        val dao = FakeSearchQueryDao()
        val localDataSource = SearchLocalDataSource(dao)
        val repository = SearchRepositoryImpl(
            remoteDataSource = SearchRemoteDataSource(FakeSearchApi()),
            localDataSource = localDataSource
        )

        // Act
        repository.saveSearchQuery(query)

        // Assert
        assertEquals(expected, dao.lastInsertedQuery)
    }

    private class FakeSearchApi(
        private val searchResult: SearchResultNetModel<List<RepoNetModel>> = SearchResultNetModel(items = emptyList())
    ) : SearchApi {
        override suspend fun getReposSearchResult(query: String): SearchResultNetModel<List<RepoNetModel>> {
            return searchResult
        }
    }

    private class FakeSearchQueryDao(
        private val queriesFlow: Flow<List<SearchQueryDbModel>> = flowOf(emptyList())
    ) : SearchQueryDao {
        var lastInsertedQuery: SearchQueryDbModel? = null
            private set

        override fun getAll(): Flow<List<SearchQueryDbModel>> {
            return queriesFlow
        }

        override suspend fun insert(query: SearchQueryDbModel) {
            lastInsertedQuery = query
        }
    }
}