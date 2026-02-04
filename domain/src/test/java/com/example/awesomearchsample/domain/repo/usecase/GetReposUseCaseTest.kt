package com.example.awesomearchsample.domain.repo.usecase

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetReposUseCaseTest {

    @Test
    fun invoke_returnsRepos() = runBlocking {
        // Arrange
        val expected = listOf(
            Repo(
                id = 1L,
                name = "Awesome",
                author = "Ada",
                description = "Sample",
                inFavorites = false
            )
        )
        val useCase = GetReposUseCase(FakeRepoRepository(expected))

        // Act
        val result = useCase.invoke()

        // Assert
        assertEquals(expected, result)
    }

    private class FakeRepoRepository(
        private val repos: List<Repo>
    ) : RepoRepository {
        override suspend fun getRepos(): List<Repo> = repos

        override suspend fun getRepoDetails(repoId: Long): RepoDetails {
            error("Not used in this test")
        }
    }
}
