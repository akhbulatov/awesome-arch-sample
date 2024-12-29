package com.example.awesomearchsample.data.search.di

import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.data.AppDatabase
import com.example.awesomearchsample.data.search.SearchRepositoryImpl
import com.example.awesomearchsample.data.search.database.SearchQueryDao
import com.example.awesomearchsample.data.search.network.SearchApi
import com.example.awesomearchsample.domain.search.repository.SearchRepository

class SearchDataFactory(
    private val appDatabase: AppDatabase,
    private val coreFactory: CoreFactory
) {

    private val searchApi: SearchApi by lazy {
        SearchApi(httpClient = coreFactory.networkFactory.httpClient)
    }

    private val searchQueryDao: SearchQueryDao by lazy {
        appDatabase.searchQueryDao()
    }

    val searchRepository: SearchRepository by lazy {
        SearchRepositoryImpl(
            searchApi = searchApi,
            searchQueryDao = searchQueryDao
        )
    }
}