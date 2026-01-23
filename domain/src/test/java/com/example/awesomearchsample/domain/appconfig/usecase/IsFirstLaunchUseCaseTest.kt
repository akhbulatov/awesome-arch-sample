package com.example.awesomearchsample.domain.appconfig.usecase

import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class IsFirstLaunchUseCaseTest {

    @Test
    fun invoke_returnsIsFirstLaunch() = runBlocking {
        // Arrange
        val expected = true
        val repository = FakeAppConfigRepository(flowOf(expected))
        val useCase = IsFirstLaunchUseCase(repository)

        // Act
        val actual = useCase.invoke().first()

        // Assert
        assertEquals(expected, actual)
    }

    private class FakeAppConfigRepository(
        private val isFirstLaunchFlow: Flow<Boolean>
    ) : AppConfigRepository {
        override fun isFirstLaunch(): Flow<Boolean> {
            return isFirstLaunchFlow
        }

        override suspend fun setIsFirstLaunch(value: Boolean) {
            error("Not used in this test")
        }
    }
}