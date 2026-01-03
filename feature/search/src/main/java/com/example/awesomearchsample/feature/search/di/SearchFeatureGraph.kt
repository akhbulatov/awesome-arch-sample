package com.example.awesomearchsample.feature.search.di

import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator

class SearchFeatureGraph(
    private val searchNavigator: SearchNavigator,
    private val domainFactory: DomainFactory,
    private val coreFactory: CoreFactory
) : SearchFeatureDependencies {

    override val searchDependencies: SearchDependencies by lazy {
        object : SearchDependencies {
            override val searchNavigator = this@SearchFeatureGraph.searchNavigator
            override val getSearchResultUseCase =
                domainFactory.searchDomainFactory.getSearchResultUseCase
            override val getSearchQueriesUseCase =
                domainFactory.searchDomainFactory.getSearchQueriesUseCase
            override val saveSearchQueryUseCase =
                domainFactory.searchDomainFactory.saveSearchQueriesUseCase
            override val uiErrorHandler = coreFactory.uiFactory.uiErrorHandler
        }
    }
}
