package com.example.awesomearchsample.domain.di

import com.example.awesomearchsample.domain.apppreferences.di.AppPreferencesDomainFactory
import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository
import com.example.awesomearchsample.domain.repo.di.RepoDomainFactory
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.search.di.SearchDomainFactory
import com.example.awesomearchsample.domain.search.repository.SearchRepository
import com.example.awesomearchsample.domain.user.di.UserDomainFactory
import com.example.awesomearchsample.domain.user.repository.UserRepository

class DomainFactory(
    repoRepository: RepoRepository,
    searchRepository: SearchRepository,
    userRepository: UserRepository,
    appPreferencesRepository: AppPreferencesRepository
) {

    val repoDomainFactory: RepoDomainFactory by lazy {
        RepoDomainFactory(
            repoRepository = repoRepository
        )
    }
    val searchDomainFactory: SearchDomainFactory by lazy {
        SearchDomainFactory(
            searchRepository = searchRepository
        )
    }
    val userDomainFactory: UserDomainFactory by lazy {
        UserDomainFactory(
            userRepository = userRepository
        )
    }
    val appPreferencesDomainFactory: AppPreferencesDomainFactory by lazy {
        AppPreferencesDomainFactory(
            appPreferencesRepository = appPreferencesRepository
        )
    }
}