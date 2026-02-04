package com.example.awesomearchsample.data.search.local.database

import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import com.example.awesomearchsample.domain.search.model.SearchQuery
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchDatabaseMapperTest {

    @Test
    fun toSearchQueryDbModel_mapsFieldsCorrectly() {
        // Arrange
        val domain = SearchQuery(value = "awesome arch")
        val expected = SearchQueryDbModel(value = "awesome arch")

        // Act
        val actual = domain.toSearchQueryDbModel()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun toSearchQueryDomain_mapsFieldsCorrectly() {
        // Arrange
        val dbModel = SearchQueryDbModel(value = "compose")
        val expected = SearchQuery(value = "compose")

        // Act
        val actual = dbModel.toSearchQueryDomain()

        // Assert
        assertEquals(expected, actual)
    }
}
