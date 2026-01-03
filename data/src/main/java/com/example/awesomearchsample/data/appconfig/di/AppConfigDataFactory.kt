package com.example.awesomearchsample.data.appconfig.di

import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.data.appconfig.AppConfigRepositoryImpl
import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository

class AppConfigDataFactory(
    private val coreFactory: CoreFactory
) {

    val appConfigRepository: AppConfigRepository by lazy {
        AppConfigRepositoryImpl(
            appPreferences = coreFactory.preferencesFactory.appPreferences
        )
    }
}
