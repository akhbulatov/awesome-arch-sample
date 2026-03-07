package com.example.awesomearchsample.data.appconfig.di

import com.example.awesomearchsample.core.preferences.di.PreferencesFactory
import com.example.awesomearchsample.data.appconfig.AppConfigRepositoryImpl
import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository

class AppConfigDataFactory(
    private val preferencesFactory: PreferencesFactory
) {

    val appConfigRepository: AppConfigRepository by lazy {
        AppConfigRepositoryImpl(
            appPreferences = preferencesFactory.appPreferences
        )
    }
}
