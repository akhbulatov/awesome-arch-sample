package com.example.awesomearchsample.di

import android.app.Application
import com.example.awesomearchsample.BuildConfig
import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.data.di.DataFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.common.di.CommonFeatureFactory

class AwesomeArchSampleFactory(
    application: Application
) {

    val coreFactory: CoreFactory by lazy {
        CoreFactory(
            context = application,
            networkBaseApiUrl = BuildConfig.NETWORK_API_BASE_URL
        )
    }

    private val dataFactory: DataFactory by lazy {
        DataFactory(
            appContext = application,
            networkFactory = coreFactory.networkFactory,
            preferencesFactory = coreFactory.preferencesFactory
        )
    }

    val domainFactory: DomainFactory by lazy {
        DomainFactory(
            repoRepositoryProvider = { dataFactory.repoDataFactory.repoRepository },
            searchRepositoryProvider = { dataFactory.searchDataFactory.searchRepository },
            userRepositoryProvider = { dataFactory.userDataFactory.userRepository },
            appSettingsRepositoryProvider = { dataFactory.appSettingsDataFactory.appSettingsRepository }
        )
    }

    val commonFeatureFactory: CommonFeatureFactory by lazy {
        CommonFeatureFactory(
            analyticsFactory = coreFactory.analyticsFactory
        )
    }

}
