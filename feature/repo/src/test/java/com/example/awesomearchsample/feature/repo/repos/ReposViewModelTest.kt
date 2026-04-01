package com.example.awesomearchsample.feature.repo.repos

import app.cash.turbine.test
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.commonapi.error.ErrorEntity
import com.example.awesomearchsample.core.testing.FakeErrorHandler
import com.example.awesomearchsample.core.testing.MainDispatcherRule
import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEvents
import com.example.awesomearchsample.feature.repo.R
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
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun init_returnsSuccess() = runTest(mainDispatcherRule.testDispatcher) {
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
        val viewModel = createViewModel(repository = repository)

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = expected),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun init_returnsError() = runTest(mainDispatcherRule.testDispatcher) {
        val repository = FakeRepoRepository(results = listOf(Result.failure(IllegalStateException("boom"))))
        val viewModel = createViewModel(
            repository = repository,
            errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        )

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    isInitialLoading = false,
                    initialError = UiError(UiText.Plain("boom"))
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun init_withEmptyList_returnsEmptyData() = runTest(mainDispatcherRule.testDispatcher) {
        val repository = FakeRepoRepository(results = listOf(Result.success(emptyList())))
        val viewModel = createViewModel(repository = repository)

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    isInitialLoading = false,
                    initialEmptyData = UiEmptyData(
                        title = UiText.Res(R.string.repos_empty_title),
                        actionText = UiText.Res(com.example.awesomearchsample.core.ui.R.string.action_refresh)
                    )
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onErrorActionClick_retriesLoading() = runTest(mainDispatcherRule.testDispatcher) {
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
        val viewModel = createViewModel(
            repository = repository,
            errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        )

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    isInitialLoading = false,
                    initialError = UiError(UiText.Plain("boom"))
                ),
                awaitItem()
            )

            viewModel.onErrorActionClick()
            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = expected),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onFavoritesClick_updatesRepo() = runTest(mainDispatcherRule.testDispatcher) {
        val repo = Repo(
            id = 1L,
            name = "Awesome",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )
        val expected = listOf(repo.copy(inFavorites = true))
        val repository = FakeRepoRepository(results = listOf(Result.success(listOf(repo))))
        val viewModel = createViewModel(repository = repository)

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = listOf(repo)),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            viewModel.onFavoritesClick(repo)

            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = expected),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onRefresh_keepsContentAndUpdatesRefreshingState() = runTest(mainDispatcherRule.testDispatcher) {
        val initial = listOf(
            Repo(
                id = 1L,
                name = "Awesome",
                author = "Ada",
                description = "Sample",
                inFavorites = false
            )
        )
        val refreshed = listOf(
            Repo(
                id = 2L,
                name = "Better",
                author = "Ada",
                description = "Updated",
                inFavorites = false
            )
        )
        val repository = FakeRepoRepository(
            results = listOf(
                Result.success(initial),
                Result.success(refreshed)
            )
        )
        val viewModel = createViewModel(repository = repository)

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = initial),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            viewModel.onRefresh()

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = initial),
                    isInitialLoading = false,
                    isRefreshing = true
                ),
                awaitItem()
            )

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = refreshed),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onRefresh_errorEmitsSnackbarEffectAndStopsRefreshing() = runTest(mainDispatcherRule.testDispatcher) {
        val initial = listOf(
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
                Result.success(initial),
                Result.failure(IllegalStateException("boom"))
            )
        )
        val viewModel = createViewModel(
            repository = repository,
            errorHandler = UiErrorHandler(FakeErrorHandler(ErrorEntity.Message("boom")))
        )

        viewModel.uiState.test {
            assertEquals(ReposUiState(), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(ReposUiState(isInitialLoading = true), awaitItem())

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = initial),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            viewModel.onRefresh()

            mainDispatcherRule.testDispatcher.scheduler.runCurrent()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = initial),
                    isInitialLoading = false,
                    isRefreshing = true
                ),
                awaitItem()
            )

            mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                ReposUiState(
                    data = ReposUiData(repos = initial),
                    isInitialLoading = false
                ),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }

        viewModel.uiEffect.test {
            assertEquals(
                ReposUiEffect.ShowErrorMessage(message = UiText.Plain("boom")),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onSearchClick_emitsNavigationEffect() = runTest(mainDispatcherRule.testDispatcher) {
        val repository = FakeRepoRepository(results = listOf(Result.success(emptyList())))
        val viewModel = createViewModel(repository = repository)

        viewModel.uiEffect.test {
            viewModel.onSearchClick()

            assertEquals(ReposUiEffect.NavigateToSearch, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onRepoClick_emitsNavigationEffect_andSendsAnalytics() = runTest(mainDispatcherRule.testDispatcher) {
        val repo = Repo(
            id = 1L,
            name = "Awesome",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )
        val repository = FakeRepoRepository(results = listOf(Result.success(listOf(repo))))
        val analyticsClient = FakeAnalyticsClient()
        val viewModel = createViewModel(
            repository = repository,
            analytics = AnalyticsEventSender(analyticsClient)
        )

        viewModel.uiEffect.test {
            viewModel.onRepoClick(repo)

            assertEquals(ReposUiEffect.NavigateToRepoDetails(repoId = 1L), awaitItem())

            val event = analyticsClient.lastEvent as AnalyticsEvents.Repo.RepoClick
            assertEquals("repo_click", event.name)
            assertEquals(1L, event.repoId)
            assertEquals("Awesome", event.repoName)

            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun createViewModel(
        repository: FakeRepoRepository,
        errorHandler: UiErrorHandler = UiErrorHandler(FakeErrorHandler()),
        analytics: AnalyticsEventSender = AnalyticsEventSender(FakeAnalyticsClient())
    ): ReposViewModel {
        return ReposViewModel(
            getReposUseCase = GetReposUseCase(repository),
            errorHandler = errorHandler,
            analyticsEventSender = analytics
        )
    }

    private class FakeRepoRepository(
        private val results: List<Result<List<Repo>>>
    ) : RepoRepository {
        private var callIndex = 0

        override suspend fun getRepos(): List<Repo> {
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
