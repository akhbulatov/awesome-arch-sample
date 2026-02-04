package com.example.awesomearchsample.domain.user.usecase

import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserDetailsUseCaseTest {

    @Test
    fun invoke_returnsUserDetails() = runBlocking {
        // Arrange
        val login = "Ada"
        val expected = UserDetails(
            id = 42L,
            login = login,
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "San Francisco",
            bio = "Software Engineer",
        )
        val repository = FakeUserRepository(expected)
        val useCase = GetUserDetailsUseCase(repository)

        // Act
        val result = useCase.invoke(login)

        // Assert
        assertEquals(expected, result)
        assertEquals(login, repository.lastRequestedLogin)
    }

    private class FakeUserRepository(
        private val userDetails: UserDetails
    ) : UserRepository {
        var lastRequestedLogin: String? = null
            private set

        override suspend fun getUserDetails(login: String): UserDetails {
            lastRequestedLogin = login
            return userDetails
        }
    }
}
