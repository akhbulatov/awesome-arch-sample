package com.example.awesomearchsample.feature.repo.repodetails

import app.cash.turbine.test
import com.example.awesomearchsample.core.common.error.ErrorEntity
import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
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

class RepoDetailsViewModelTest {

    @Rule
    @JvmField
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun init_returnsSuccess() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repoId = 1L
        val expected = RepoDetails(
            id = 1L,
            name = "Repo 1",
            author = "Ada",
            description = "Description",
            starsCount = 100,
            forksCount = 50
        )
        val repository = FakeRepoRepository(results = listOf(Result.success(expected)))
        val useCase = GetRepoDetailsUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler())

        // Act
        val viewModel = RepoDetailsViewModel(repoId, useCase, errorHandler)

        // Assert
        viewModel.uiState.test {
            assertEquals(RepoDetailsUiState.Idle, awaitItem())

            // Loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(RepoDetailsUiState.Loading, awaitItem())

            // Success phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(RepoDetailsUiState.Success(expected), awaitItem())

            // Argument forwarding
            assertEquals(repoId, repository.lastRequestedRepoId)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun init_returnsError() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repoId = 1L
        val exception = IllegalStateException("boom")
        val repository = FakeRepoRepository(results = listOf(Result.failure(exception)))
        val useCase = GetRepoDetailsUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))

        // Act
        val viewModel = RepoDetailsViewModel(repoId, useCase, errorHandler)

        // Assert
        viewModel.uiState.test {
            assertEquals(RepoDetailsUiState.Idle, awaitItem())

            // Loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(RepoDetailsUiState.Loading, awaitItem())

            // Error phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(RepoDetailsUiState.Error(UiError(UiText.Plain("boom"))), awaitItem())

            // Argument forwarding
            assertEquals(repoId, repository.lastRequestedRepoId)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onErrorActionClick_retriesLoading() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repoId = 1L
        val expected = RepoDetails(
            id = 1L,
            name = "Repo 1",
            author = "Ada",
            description = "Description",
            starsCount = 100,
            forksCount = 50
        )
        val repository = FakeRepoRepository(
            results = listOf(
                Result.failure(IllegalStateException("boom")),
                Result.success(expected)
            )
        )
        val useCase = GetRepoDetailsUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        val viewModel = RepoDetailsViewModel(repoId, useCase, errorHandler)

        // Assert
        viewModel.uiState.test {
            assertEquals(RepoDetailsUiState.Idle, awaitItem())

            // Initial loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(RepoDetailsUiState.Loading, awaitItem())

            // Error phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(RepoDetailsUiState.Error(UiError(UiText.Plain("boom"))), awaitItem())

            // Act
            viewModel.onErrorActionClick()
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            // Retry loading phase
            assertEquals(RepoDetailsUiState.Loading, awaitItem())
            // Retry success phase
            assertEquals(RepoDetailsUiState.Success(expected), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    private class FakeRepoRepository(
        private val results: List<Result<RepoDetails>> // Sequence of outcomes for successive calls.
    ) : RepoRepository {
        var lastRequestedRepoId: Long? = null
            private set
        private var callIndex = 0 // Tracks which result to return next.

        override suspend fun getRepoDetails(repoId: Long): RepoDetails {
            lastRequestedRepoId = repoId
            // Returns sequential Result values to simulate failure-then-success flows.
            val result = results.getOrNull(callIndex) ?: results.last()
            callIndex += 1
            return result.getOrThrow()
        }

        override suspend fun getRepos(): List<Repo> {
            error("Not used in this test")
        }
    }

    private class FakeErrorHandler(
        private val errorEntity: ErrorEntity = ErrorEntity.Message("error")
    ) : ErrorHandler {
        override suspend fun getError(throwable: Throwable): ErrorEntity = errorEntity

        override fun recordError(throwable: Throwable) = Unit
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        val testDispatcher: TestDispatcher = StandardTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description?) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description?) {
            Dispatchers.resetMain()
        }
    }
}