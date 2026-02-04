package com.example.awesomearchsample.feature.repo.repos

import app.cash.turbine.test
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.common.error.ErrorEntity
import com.example.awesomearchsample.core.testing.FakeErrorHandler
import com.example.awesomearchsample.core.testing.MainDispatcherRule
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEvents
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReposViewModelTest {

    @Rule
    @JvmField
    // Replaces Dispatchers.Main for viewModelScope.
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun init_returnsSuccess() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val expected = listOf(
            Repo(
                id = 1L,
                name = "Awesome",
                author = "Ada",
                description = "Sample",
                inFavorites = false
            )
        )
        val repository = FakeRepoRepository(results = listOf(Result.success(expected)))
        val useCase = GetReposUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler())
        val analytics = AnalyticsEventSender(FakeAnalyticsClient())

        // Act
        val viewModel = ReposViewModel(useCase, errorHandler, analytics)

        // Assert
        viewModel.uiState.test {
            assertEquals(ReposUiState.Idle, awaitItem())

            // Loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState.Loading, awaitItem())

            // Success phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ReposUiState.Success(expected), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun init_returnsError() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repository = FakeRepoRepository(results = listOf(Result.failure(IllegalStateException("boom"))))
        val useCase = GetReposUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        val analytics = AnalyticsEventSender(FakeAnalyticsClient())

        // Act
        val viewModel = ReposViewModel(useCase, errorHandler, analytics)

        // Assert
        viewModel.uiState.test {
            assertEquals(ReposUiState.Idle, awaitItem())

            // Loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState.Loading, awaitItem())

            // Error phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ReposUiState.Error(UiError(UiText.Plain("boom"))), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onErrorActionClick_retriesLoading() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val expected = listOf(
            Repo(
                id = 1L,
                name = "Awesome",
                author = "Ada",
                description = "Sample",
                inFavorites = false
            )
        )
        val repository = FakeRepoRepository(
            results = listOf(
                Result.failure(IllegalStateException("boom")),
                Result.success(expected)
            )
        )
        val useCase = GetReposUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        val analytics = AnalyticsEventSender(FakeAnalyticsClient())
        val viewModel = ReposViewModel(useCase, errorHandler, analytics)

        // Assert
        viewModel.uiState.test {
            assertEquals(ReposUiState.Idle, awaitItem())

            // Initial loading phase
            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState.Loading, awaitItem())

            // Error phase
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ReposUiState.Error(UiError(UiText.Plain("boom"))), awaitItem())

            // Act
            viewModel.onErrorActionClick()
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            // Retry loading phase
            assertEquals(ReposUiState.Loading, awaitItem())
            // Retry success phase
            assertEquals(ReposUiState.Success(expected), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onFavoritesClick_updatesRepo() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repo = Repo(
            id = 1L,
            name = "Awesome",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )
        val expected = listOf(repo.copy(inFavorites = true))
        val repository = FakeRepoRepository(results = listOf(Result.success(listOf(repo))))
        val useCase = GetReposUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler())
        val analytics = AnalyticsEventSender(FakeAnalyticsClient())
        val viewModel = ReposViewModel(useCase, errorHandler, analytics)

        // Assert
        viewModel.uiState.test {
            assertEquals(ReposUiState.Idle, awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState.Loading, awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ReposUiState.Success(listOf(repo)), awaitItem())

            // Act
            viewModel.onFavoritesClick(repo)

            // Assert
            assertEquals(ReposUiState.Success(expected), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onSearchClick_emitsNavigationEffect() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repository = FakeRepoRepository(results = listOf(Result.success(emptyList())))
        val useCase = GetReposUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler())
        val analytics = AnalyticsEventSender(FakeAnalyticsClient())
        val viewModel = ReposViewModel(useCase, errorHandler, analytics)

        // Assert
        viewModel.uiEffect.test {
            // Act
            viewModel.onSearchClick()

            // Assert
            assertEquals(ReposUiEffect.NavigateToSearch, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onRepoClick_emitsNavigationEffect_andSendsAnalytics() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        val repo = Repo(
            id = 1L,
            name = "Awesome",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )
        val repository = FakeRepoRepository(results = listOf(Result.success(listOf(repo))))
        val useCase = GetReposUseCase(repository)
        val errorHandler = UiErrorHandler(FakeErrorHandler())
        val analyticsClient = FakeAnalyticsClient()
        val analytics = AnalyticsEventSender(analyticsClient)
        val viewModel = ReposViewModel(useCase, errorHandler, analytics)

        // Assert
        viewModel.uiEffect.test {
            // Act
            viewModel.onRepoClick(repo)

            // Assert
            assertEquals(ReposUiEffect.NavigateToRepoDetails(repoId = 1L), awaitItem())

            val event = analyticsClient.lastEvent as AnalyticsEvents.Repo.RepoClick
            assertEquals("repo_click", event.name)
            assertEquals(1L, event.repoId)
            assertEquals("Awesome", event.repoName)

            cancelAndIgnoreRemainingEvents()
        }
    }

    private class FakeRepoRepository(
        private val results: List<Result<List<Repo>>> // Sequence of outcomes for successive calls.
    ) : RepoRepository {
        private var callIndex = 0 // Tracks which result to return next.

        override suspend fun getRepos(): List<Repo> {
            // Returns sequential Result values to simulate failure-then-success flows.
            val result = results.getOrNull(callIndex) ?: results.last()
            callIndex += 1
            return result.getOrThrow()
        }

        override suspend fun getRepoDetails(repoId: Long): RepoDetails {
            error("Not used in this test")
        }
    }

    private class FakeAnalyticsClient : AnalyticsClient {
        var lastEvent: AnalyticsEvent? = null
            private set

        override fun sendEvent(event: AnalyticsEvent) {
            lastEvent = event
        }
    }
}
