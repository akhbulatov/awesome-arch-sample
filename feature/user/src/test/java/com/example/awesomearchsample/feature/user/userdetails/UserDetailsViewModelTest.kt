package com.example.awesomearchsample.feature.user.userdetails

import app.cash.turbine.test
import com.example.awesomearchsample.core.common.error.ErrorEntity
import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailsViewModelTest {

    @Rule
    @JvmField
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun init_returnsSuccess() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val login = "ada"
        val expected = UserDetails(
            id = 1L,
            login = login,
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "San Francisco",
            bio = "Software Engineer"
        )
        val repository = FakeUserRepository(results = listOf(Result.success(expected)))
        val useCase = GetUserDetailsUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler())

        // Act
        val viewModel = UserDetailsViewModel(login, useCase, errorHandler)

        // Assert
        viewModel.uiState.test {
            assertEquals(UserDetailsUiState.Idle, awaitItem())

            // Loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(UserDetailsUiState.Loading, awaitItem())

            // Success phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(UserDetailsUiState.Success(expected), awaitItem())

            // Argument forwarding
            assertEquals(login, repository.lastRequestedLogin)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun init_returnsError() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val login = "ada"
        val exception = IllegalStateException("boom")
        val repository = FakeUserRepository(results = listOf(Result.failure(exception)))
        val useCase = GetUserDetailsUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))

        // Act
        val viewModel = UserDetailsViewModel(login, useCase, errorHandler)

        // Assert
        viewModel.uiState.test {
            assertEquals(UserDetailsUiState.Idle, awaitItem())

            // Loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(UserDetailsUiState.Loading, awaitItem())

            // Error phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(UserDetailsUiState.Error(UiError(UiText.Plain("boom"))), awaitItem())

            // Argument forwarding
            assertEquals(login, repository.lastRequestedLogin)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onErrorActionClick_retriesLoading() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val login = "ada"
        val expected = UserDetails(
            id = 1L,
            login = login,
            name = "Ada Lovelace",
            avatarUrl = "https://example.com/avatar.png",
            location = "San Francisco",
            bio = "Software Engineer"
        )
        val repository = FakeUserRepository(
            results = listOf(
                Result.failure(IllegalStateException("boom")),
                Result.success(expected)
            )
        )
        val useCase = GetUserDetailsUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        val viewModel = UserDetailsViewModel(login, useCase, errorHandler)

        // Assert
        viewModel.uiState.test {
            assertEquals(UserDetailsUiState.Idle, awaitItem())

            // Initial loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(UserDetailsUiState.Loading, awaitItem())

            // Error phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(UserDetailsUiState.Error(UiError(UiText.Plain("boom"))), awaitItem())

            // Act
            viewModel.onErrorActionClick()
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            // Retry loading phase
            assertEquals(UserDetailsUiState.Loading, awaitItem())
            // Retry success phase
            assertEquals(UserDetailsUiState.Success(expected), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    private class FakeUserRepository(
        private val results: List<Result<UserDetails>>
    ) : UserRepository {
        var lastRequestedLogin: String? = null
            private set
        private var callIndex = 0

        override suspend fun getUserDetails(login: String): UserDetails {
            lastRequestedLogin = login
            val result = results.getOrNull(callIndex) ?: results.last()
            callIndex += 1
            return result.getOrThrow()
        }
    }

    private class FakeErrorHandler(
        private val errorEntity: ErrorEntity = ErrorEntity.Message("error")
    ) : ErrorHandler {
        override suspend fun getError(throwable: Throwable): ErrorEntity = errorEntity

        override fun recordError(throwable: Throwable) = Unit
    }

    class MainDispatcherRule(
        val testDispatcher: TestDispatcher = StandardTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }
}
