package com.example.awesomearchsample.data.repo.remote

import com.example.awesomearchsample.data.repo.remote.network.RepoApi
import com.example.awesomearchsample.data.repo.remote.network.model.OwnerNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoDetailsNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RepoRemoteDataSourceTest {

    @Test
    fun getRepos_returnsMappedRepos() = runBlocking {
        // Arrange
        val netModels = listOf(
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
        val expected = listOf(
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
        val api = FakeRepoApi(repos = netModels)
        val dataSource = RepoRemoteDataSource(api)

        // Act
        val actual = dataSource.getRepos()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun getRepoDetails_returnsMappedRepoDetails() = runBlocking {
        // Arrange
        val repoId = 42L
        val netModel = RepoDetailsNetModel(
            id = repoId,
            name = "Details",
            owner = OwnerNetModel(login = "Ada"),
            description = "Sample",
            stargazersCount = 10,
            forksCount = 2
        )
        val expected = RepoDetails(
            id = repoId,
            name = "Details",
            author = "Ada",
            description = "Sample",
            starsCount = 10,
            forksCount = 2
        )
        val api = FakeRepoApi(repoDetails = netModel)
        val dataSource = RepoRemoteDataSource(api)

        // Act
        val actual = dataSource.getRepoDetails(repoId)

        // Assert
        assertEquals(expected, actual)
    }

    private class FakeRepoApi(
        private val repos: List<RepoNetModel> = emptyList(),
        private val repoDetails: RepoDetailsNetModel? = null
    ) : RepoApi {
        override suspend fun getRepos(): List<RepoNetModel> = repos

        override suspend fun getRepoDetails(repoId: Long): RepoDetailsNetModel {
            return requireNotNull(repoDetails)
        }
    }
}
