package com.example.awesomearchsample.data.repo.remote.network

import com.example.awesomearchsample.data.repo.remote.network.model.OwnerNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoDetailsNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class RepoNetworkMapperTest {

    @Test
    fun toRepoDomain_mapsNetworkModel() {
        // Arrange
        val netModel = RepoNetModel(
            id = 1L,
            name = "Awesome",
            owner = OwnerNetModel(login = "Ada"),
            description = "Sample"
        )
        val expected = Repo(
            id = 1L,
            name = "Awesome",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )

        // Act
        val actual = netModel.toRepoDomain()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun toRepoDetailsDomain_mapsNetworkModel() {
        // Arrange
        val netModel = RepoDetailsNetModel(
            id = 2L,
            name = "Awesome Details",
            owner = OwnerNetModel(login = "Bob"),
            description = null,
            stargazersCount = 10,
            forksCount = 2
        )
        val expected = RepoDetails(
            id = 2L,
            name = "Awesome Details",
            author = "Bob",
            description = null,
            starsCount = 10,
            forksCount = 2
        )

        // Act
        val actual = netModel.toRepoDetailsDomain()

        // Assert
        assertEquals(expected, actual)
    }
}
