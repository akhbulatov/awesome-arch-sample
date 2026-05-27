package com.example.awesomearchsample.domain.appsettings.usecase

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SetIsFirstLaunchUseCaseTest {

    @Test
    fun invoke_setsIsFirstLaunch() = runBlocking {
        // Arrange
        val expected = false
        val repository = FakeAppSettingsRepository()
        val useCase = SetIsFirstLaunchUseCase(repository)

        // Act
        useCase(expected)

        // Assert
        assertEquals(expected, repository.lastSetValue)
    }

    private class FakeAppSettingsRepository : AppSettingsRepository {
        var lastSetValue: Boolean? = null
            private set

        override fun isFirstLaunch(): Flow<Boolean> {
            error("Not used in this test")
        }

        override suspend fun setIsFirstLaunch(value: Boolean) {
            lastSetValue = value
        }

        override fun isNotificationsEnabled(): Flow<Boolean> {
            error("Not used in this test")
        }

        override suspend fun setNotificationsEnabled(value: Boolean) {
            error("Not used in this test")
        }
    }
}
