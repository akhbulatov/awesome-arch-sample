package com.example.awesomearchsample.domain.di

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class DomainFactoryTest {

    @Test
    fun searchRepositoryProvider_runsOnceWhenSearchDependenciesAreFirstRequested() {
        var searchProviderCalls = 0
        val domainFactory = DomainFactory(
            repoRepositoryProvider = { error("Repo repository was requested") },
            searchRepositoryProvider = {
                searchProviderCalls++
                FakeSearchRepository
            },
            userRepositoryProvider = { error("User repository was requested") },
            appSettingsRepositoryProvider = { FakeAppSettingsRepository }
        )

        domainFactory.appSettingsDomainFactory
        assertEquals(0, searchProviderCalls)

        val searchDomainFactory = domainFactory.searchDomainFactory
        assertEquals(1, searchProviderCalls)

        assertSame(searchDomainFactory, domainFactory.searchDomainFactory)
        assertEquals(1, searchProviderCalls)
    }

    private object FakeAppSettingsRepository : AppSettingsRepository {
        override fun isFirstLaunch() = flowOf(true)
        override suspend fun setIsFirstLaunch(value: Boolean) = Unit
        override fun isNotificationsEnabled() = flowOf(false)
        override suspend fun setNotificationsEnabled(value: Boolean) = Unit
    }

    private object FakeSearchRepository : SearchRepository {
        override suspend fun getSearchResult(query: String) = SearchResult.Repos(emptyList())
        override fun getSearchQueries() = flowOf(emptyList<SearchQuery>())
        override suspend fun saveSearchQuery(query: SearchQuery) = Unit
    }
}
