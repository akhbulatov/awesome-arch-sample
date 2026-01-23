package com.example.awesomearchsample.data.user.remote

import com.example.awesomearchsample.data.user.remote.network.UserApi
import com.example.awesomearchsample.data.user.remote.network.model.UserDetailsNetModel
import com.example.awesomearchsample.domain.user.model.UserDetails
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRemoteDataSourceTest {

    @Test
    fun getUserDetails_returnsMappedUserDetails() = runBlocking {
        // Arrange
        val login = "Ada"
        val netModel = UserDetailsNetModel(
            id = 1L,
            login = "Ada",
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "London",
            bio = "Sample bio"
        )
        val expected = UserDetails(
            id = 1L,
            login = "Ada",
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "London",
            bio = "Sample bio"
        )
        val api = FakeUserApi(netModel)
        val dataSource = UserRemoteDataSource(api)

        // Act
        val actual = dataSource.getUserDetails(login)

        // Assert
        assertEquals(expected, actual)
    }

    private class FakeUserApi(
        private val userDetails: UserDetailsNetModel
    ) : UserApi {
        override suspend fun getUserDetails(login: String): UserDetailsNetModel {
            return userDetails
        }
    }
}