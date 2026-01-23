package com.example.awesomearchsample.data.user

import com.example.awesomearchsample.data.user.remote.UserRemoteDataSource
import com.example.awesomearchsample.data.user.remote.network.UserApi
import com.example.awesomearchsample.data.user.remote.network.model.UserDetailsNetModel
import com.example.awesomearchsample.domain.user.model.UserDetails
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryImplTest {

    @Test
    fun getUserDetails_returnsRemoteUserDetails() = runBlocking {
        // Arrange
        val login = "ada"
        val netModel = UserDetailsNetModel(
            id = 1L,
            login = login,
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "San Francisco",
            bio = "Software Engineer"
        )
        val expected = UserDetails(
            id = 1L,
            login = login,
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "San Francisco",
            bio = "Software Engineer"
        )
        val api = FakeUserApi(netModel)
        val remote = UserRemoteDataSource(api)
        val repository = UserRepositoryImpl(remote)

        // Act
        val actual = repository.getUserDetails(login)

        // Assert
        assertEquals(expected, actual)
        assertEquals(login, api.lastRequestedLogin)
    }

    private class FakeUserApi(
        private val userDetails: UserDetailsNetModel
    ) : UserApi {
        var lastRequestedLogin: String? = null
            private set

        override suspend fun getUserDetails(login: String): UserDetailsNetModel {
            lastRequestedLogin = login
            return userDetails
        }
    }
}
