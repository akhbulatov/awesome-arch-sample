package com.example.awesomearchsample

import android.content.Context
import com.example.awesomearchsample.core.commonfactory.di.CoreFactory
import com.example.awesomearchsample.data.di.DataFactory
import com.example.awesomearchsample.di.NavigationFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.common.di.CommonFeatureFactory

class AwesomeArchSampleFactory(
    context: Context
) {

    val coreFactory: CoreFactory by lazy {
        CoreFactory(
            context = context
        )
    }

    private val dataFactory: DataFactory by lazy {
        DataFactory(
            coreFactory = coreFactory
        )
    }

    val domainFactory: DomainFactory by lazy {
        DomainFactory(
            repoRepository = dataFactory.repoDataFactory.repoRepository,
            searchRepository = dataFactory.searchDataFactory.searchRepository,
            userRepository = dataFactory.userDataFactory.userRepository,
            appPreferencesRepository = dataFactory.appPreferencesDataFactory.appPreferencesRepository
        )
    }

    val commonFeatureFactory: CommonFeatureFactory by lazy {
        CommonFeatureFactory(
            coreFactory = coreFactory
        )
    }

    val navigationFactory: NavigationFactory by lazy {
        NavigationFactory()
    }
}