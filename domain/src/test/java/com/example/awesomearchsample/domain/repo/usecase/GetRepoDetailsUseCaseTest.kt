package com.example.awesomearchsample.domain.repo.usecase

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetRepoDetailsUseCaseTest {

    @Test
    fun invoke_returnsRepoDetails() = runBlocking {
        // Arrange
        val repoId = 42L
        val expected = RepoDetails(
            id = repoId,
            name = "Awesome",
            author = "Ada",
            description = "Details",
            starsCount = 10,
            forksCount = 2
        )
        val repository = FakeRepoRepository(expected)
        val useCase = GetRepoDetailsUseCase(repository)

        // Act
        val result = useCase.invoke(repoId)

        // Assert
        assertEquals(expected, result)
        assertEquals(repoId, repository.lastRequestedRepoId)
    }

    private class FakeRepoRepository(
        private val repoDetails: RepoDetails
    ) : RepoRepository {
        var lastRequestedRepoId: Long? = null
            private set

        override suspend fun getRepos(): List<Repo> {
            error("Not used in this test")
        }

        override suspend fun getRepoDetails(repoId: Long): RepoDetails {
            lastRequestedRepoId = repoId
            return repoDetails
        }
    }
}
