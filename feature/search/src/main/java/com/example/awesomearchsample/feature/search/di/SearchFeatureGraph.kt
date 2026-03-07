package com.example.awesomearchsample.feature.search.di

import com.example.awesomearchsample.core.ui.di.UiFactory
import com.example.awesomearchsample.domain.di.DomainFactory

class SearchFeatureGraph(
    private val domainFactory: DomainFactory,
    private val uiFactory: UiFactory
) : SearchFeatureDependencies {

    override val searchScreenDependencies: SearchScreenDependencies by lazy {
        object : SearchScreenDependencies {
            override val getSearchResultUseCase =
                domainFactory.searchDomainFactory.getSearchResultUseCase
            override val getSearchQueriesUseCase =
                domainFactory.searchDomainFactory.getSearchQueriesUseCase
            override val saveSearchQueryUseCase =
                domainFactory.searchDomainFactory.saveSearchQueriesUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
        }
    }
}
