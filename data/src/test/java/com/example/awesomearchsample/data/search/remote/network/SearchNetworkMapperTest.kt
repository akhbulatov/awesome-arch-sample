package com.example.awesomearchsample.data.search.remote.network

import com.example.awesomearchsample.data.repo.remote.network.model.OwnerNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.remote.network.model.SearchResultNetModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchResult
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchNetworkMapperTest {

    @Test
    fun toSearchResultReposDomain_mapsFieldsCorrectly() {
        // Arrange
        val netModel = SearchResultNetModel(
            items = listOf(
                RepoNetModel(
                    id = 1L,
                    name = "Awesome",
                    owner = OwnerNetModel(login = "Ada"),
                    description = "Sample"
                ),
                RepoNetModel(
                    id = 2L,
                    name = "Awesome 2",
                    owner = OwnerNetModel(login = "Bob"),
                    description = null
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
                ),
                Repo(
                    id = 2L,
                    name = "Awesome 2",
                    author = "Bob",
                    description = null,
                    inFavorites = false
                )
            )
        )

        // Act
        val actual = netModel.toSearchResultReposDomain()

        // Assert
        assertEquals(expected, actual)
    }
}
