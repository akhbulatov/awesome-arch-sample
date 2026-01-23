package com.example.awesomearchsample.data.search.local

import com.example.awesomearchsample.data.search.local.database.SearchQueryDao
import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import com.example.awesomearchsample.data.search.local.database.toSearchQueryDbModel
import com.example.awesomearchsample.domain.search.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchLocalDataSourceTest {

    @Test
    fun getSearchQueries_returnsMappedQueries() = runBlocking {
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
        val dataSource = SearchLocalDataSource(dao)

        // Act
        val actual = dataSource.getSearchQueries().first()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun saveSearchQuery_insertsMappedQuery() = runBlocking {
        // Arrange
        val query = SearchQuery(value = "awesome arch")
        val expected = query.toSearchQueryDbModel()
        val dao = FakeSearchQueryDao()
        val dataSource = SearchLocalDataSource(dao)

        // Act
        dataSource.saveSearchQuery(query)

        // Assert
        assertEquals(expected, dao.lastInsertedQuery)
    }

    private class FakeSearchQueryDao(
        private val queriesFlow: Flow<List<SearchQueryDbModel>> = flowOf(emptyList())
    ) : SearchQueryDao {
        var lastInsertedQuery: SearchQueryDbModel? = null
            private set

        override fun getAll(): Flow<List<SearchQueryDbModel>> = queriesFlow

        override suspend fun insert(query: SearchQueryDbModel) {
            lastInsertedQuery = query
        }
    }
}
