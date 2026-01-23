package com.example.awesomearchsample.domain.user.usecase

import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserDetailsUseCaseTest {

    @Test
    fun invoke_returnsUserDetailsFromRepository() = runBlocking {
        // Arrange
        val expected = UserDetails(
            id = 42L,
            login = "Ada",
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "San Francisco",
            bio = "Software Engineer",
        )
        val useCase = GetUserDetailsUseCase(FakeUserRepository(expected))

        // Act
        val result = useCase.invoke("Ada")

        // Assert
        assertEquals(expected, result)
    }

    private class FakeUserRepository(
        private val userDetails: UserDetails
    ) : UserRepository {
        override suspend fun getUserDetails(login: String): UserDetails = userDetails
    }
}