package com.example.awesomearchsample.domain.repo.usecase

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetRepoDetailsUseCaseTest {

    @Test
    fun invoke_returnsRepoDetailsFromRepository() = runBlocking {
        // Arrange
        val expected = RepoDetails(
            id = 42L,
            name = "Awesome",
            author = "Ada",
            description = "Details",
            starsCount = 10,
            forksCount = 2
        )
        val useCase = GetRepoDetailsUseCase(FakeRepoRepository(expected))

        // Act
        val result = useCase.invoke(42L)

        // Assert
        assertEquals(expected, result)
    }

    private class FakeRepoRepository(
        private val repoDetails: RepoDetails
    ) : RepoRepository {
        override suspend fun getRepos(): List<Repo> {
            error("Not used in this test")
        }

        override suspend fun getRepoDetails(repoId: Long): RepoDetails = repoDetails
    }
}
