package com.example.awesomearchsample.domain.di

import com.example.awesomearchsample.domain.appsettings.di.AppSettingsDomainFactory
import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import com.example.awesomearchsample.domain.repo.di.RepoDomainFactory
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.search.di.SearchDomainFactory
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import com.example.awesomearchsample.domain.user.di.UserDomainFactory
import com.example.awesomearchsample.domain.user.repository.UserRepository

class DomainFactory(
    repoRepositoryProvider: () -> RepoRepository,
    searchRepositoryProvider: () -> SearchRepository,
    userRepositoryProvider: () -> UserRepository,
    appSettingsRepositoryProvider: () -> AppSettingsRepository
) {

    val repoDomainFactory: RepoDomainFactory by lazy {
        RepoDomainFactory(
            repoRepository = repoRepositoryProvider()
        )
    }
    val searchDomainFactory: SearchDomainFactory by lazy {
        SearchDomainFactory(
            searchRepository = searchRepositoryProvider()
        )
    }
    val userDomainFactory: UserDomainFactory by lazy {
        UserDomainFactory(
            userRepository = userRepositoryProvider()
        )
    }
    val appSettingsDomainFactory: AppSettingsDomainFactory by lazy {
        AppSettingsDomainFactory(
            appSettingsRepository = appSettingsRepositoryProvider()
        )
    }
}
