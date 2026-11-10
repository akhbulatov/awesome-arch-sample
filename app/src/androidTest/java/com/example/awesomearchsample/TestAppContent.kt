package com.example.awesomearchsample

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.testing.FakeErrorHandler
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.di.UiFactory
import com.example.awesomearchsample.di.AppDependencies
import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureGraph
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsScreenDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureGraph
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureGraph
import com.example.awesomearchsample.feature.user.di.UserFeatureGraph
import com.example.awesomearchsample.navigation.AppNavHost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException

internal const val TEST_REPO_ID = 42L
internal const val TEST_AUTHOR_NAME = "Sample Author"
private const val TEST_AUTHOR_LOGIN = "sample-author"

internal fun testAppContent(
    appSettingsRepository: AppSettingsRepository = FakeAppSettingsRepository()
): @Composable () -> Unit {
    val domainFactory = DomainFactory(
        repoRepositoryProvider = { FakeRepoRepository() },
        searchRepositoryProvider = { FakeSearchRepository },
        userRepositoryProvider = { FakeUserRepository },
        appSettingsRepositoryProvider = { appSettingsRepository }
    )
    val uiFactory = UiFactory(FakeErrorHandler())
    val repoGraph = object : RepoFeatureDependencies {
        override val reposScreenDependencies: ReposScreenDependencies by lazy {
            object : ReposScreenDependencies {
                override val getReposUseCase = domainFactory.repoDomainFactory.getReposUseCase
                override val uiErrorHandler = uiFactory.uiErrorHandler
                override val analyticsEventSender = AnalyticsEventSender(NoOpAnalyticsClient)
            }
        }
        override val repoDetailsScreenDependencies: RepoDetailsScreenDependencies by lazy {
            object : RepoDetailsScreenDependencies {
                override val getRepoDetailsUseCase = domainFactory.repoDomainFactory.getRepoDetailsUseCase
                override val uiErrorHandler = uiFactory.uiErrorHandler
            }
        }
    }
    val dependencies = object : AppDependencies {
        override val launchFeatureDependencies = LaunchFeatureGraph(domainFactory)
        override val repoFeatureDependencies = repoGraph
        override val searchFeatureDependencies = SearchFeatureGraph(domainFactory, uiFactory)
        override val settingsFeatureDependencies = SettingsFeatureGraph(domainFactory)
        override val userFeatureDependencies = UserFeatureGraph(domainFactory, uiFactory)
    }

    return {
        AppTheme {
            AppNavHost(dependencies = dependencies)
        }
    }
}

internal class FakeAppSettingsRepository(
    private val failOnRead: Boolean = false,
    private val failOnWrite: Boolean = false
) : AppSettingsRepository {
    private val firstLaunchRecorded = MutableStateFlow(false)
    private val notificationsEnabled = MutableStateFlow(false)

    override fun isFirstLaunch(): Flow<Boolean> =
        if (failOnRead) flow { throw IOException("Could not read first-launch flag") }
        else firstLaunchRecorded

    override suspend fun setIsFirstLaunch(value: Boolean) {
        if (failOnWrite) throw IOException("Could not write first-launch flag")
        firstLaunchRecorded.value = value
    }

    override fun isNotificationsEnabled(): Flow<Boolean> = notificationsEnabled

    override suspend fun setNotificationsEnabled(value: Boolean) {
        notificationsEnabled.value = value
    }
}

private class FakeRepoRepository : RepoRepository {
    private val repo = Repo(
        id = TEST_REPO_ID,
        name = "Sample Repository",
        author = TEST_AUTHOR_LOGIN,
        description = null
    )

    override suspend fun getRepos(): List<Repo> = listOf(repo)

    override suspend fun getRepoDetails(repoId: Long): RepoDetails {
        check(repoId == repo.id)
        return RepoDetails(
            id = repo.id,
            name = repo.name,
            author = repo.author,
            description = repo.description,
            starsCount = 1,
            forksCount = 0
        )
    }
}

private object FakeSearchRepository : SearchRepository {
    override suspend fun getSearchResult(query: String): SearchResult = SearchResult.Repos(emptyList())

    override fun getSearchQueries(): Flow<List<SearchQuery>> = flowOf(emptyList())

    override suspend fun saveSearchQuery(query: SearchQuery) = Unit
}

private object FakeUserRepository : UserRepository {
    override suspend fun getUserDetails(login: String): UserDetails {
        check(login == TEST_AUTHOR_LOGIN)
        return UserDetails(
            id = 1L,
            login = login,
            name = TEST_AUTHOR_NAME,
            avatarUrl = null,
            location = null,
            bio = null
        )
    }
}

private object NoOpAnalyticsClient : AnalyticsClient {
    override fun sendEvent(event: AnalyticsEvent) = Unit
}
