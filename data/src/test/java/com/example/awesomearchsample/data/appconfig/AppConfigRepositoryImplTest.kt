package com.example.awesomearchsample.data.appconfig

import com.example.awesomearchsample.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class AppConfigRepositoryImplTest {

    @Test
    fun isFirstLaunch_returnsPreferencesValue() = runBlocking {
        // Arrange
        val expected = true
        val preferences = FakeAppPreferences(flowOf(expected))
        val repository = AppConfigRepositoryImpl(preferences)

        // Act
        val actual = repository.isFirstLaunch().first()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun setIsFirstLaunch_savesValue() = runBlocking {
        // Arrange
        val expected = false
        val preferences = FakeAppPreferences()
        val repository = AppConfigRepositoryImpl(preferences)

        // Act
        repository.setIsFirstLaunch(expected)

        // Assert
        assertEquals(expected, preferences.lastSetValue)
    }

    private class FakeAppPreferences(
        private val isFirstLaunchFlow: Flow<Boolean> = flowOf(false)
    ) : AppPreferences {
        var lastSetValue: Boolean? = null
            private set

        override fun isFirstLaunch(): Flow<Boolean> = isFirstLaunchFlow

        override suspend fun setIsFirstLaunch(value: Boolean) {
            lastSetValue = value
        }
    }
}
