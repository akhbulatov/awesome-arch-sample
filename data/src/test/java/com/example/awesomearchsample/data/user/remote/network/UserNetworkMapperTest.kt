package com.example.awesomearchsample.data.user.remote.network

import com.example.awesomearchsample.data.user.remote.network.model.UserDetailsNetModel
import com.example.awesomearchsample.domain.user.model.UserDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class UserNetworkMapperTest {

    @Test
    fun toUserDetailsDomain_mapsFieldsCorrectly() {
        // Arrange
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

        // Act
        val actual = netModel.toUserDetailsDomain()

        // Assert
        assertEquals(expected, actual)
    }
}