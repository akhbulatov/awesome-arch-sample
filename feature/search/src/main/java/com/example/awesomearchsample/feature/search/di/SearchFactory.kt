package com.example.awesomearchsample.feature.search.di

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.search.SearchViewModel
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator

class SearchFactory(
    private val searchNavigator: SearchNavigator,
    private val domainFactory: DomainFactory,
    private val coreFactory: CoreFactory,
) {

    fun createViewModelFactory() = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(
                searchNavigator = searchNavigator,
                getSearchResultUseCase = domainFactory.searchDomainFactory.getSearchResultUseCase,
                getSearchQueriesUseCase = domainFactory.searchDomainFactory.getSearchQueriesUseCase,
                saveSearchQueryUseCase = domainFactory.searchDomainFactory.saveSearchQueriesUseCase,
                errorHandler = coreFactory.uiFactory.uiErrorHandler,
                resourceManager = coreFactory.uiFactory.resourceManager
            ) as T
        }
    }
}

interface SearchFactoryProvider {
    val searchFactory: SearchFactory
}

@Composable
fun getSearchFactory(): SearchFactory {
    val application = (LocalContext.current.applicationContext as Application)
    return (application as SearchFactoryProvider).searchFactory
}