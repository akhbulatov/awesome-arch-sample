package com.example.awesomearchsample.data.appsettings.di

import com.example.awesomearchsample.core.preferences.di.PreferencesFactory
import com.example.awesomearchsample.data.appsettings.AppSettingsRepositoryImpl
import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository

class AppSettingsDataFactory(
    private val preferencesFactory: PreferencesFactory
) {

    val appSettingsRepository: AppSettingsRepository by lazy {
        AppSettingsRepositoryImpl(
            appPreferences = preferencesFactory.appPreferences
        )
    }
}
