package com.example.awesomearchsample.data.appsettings.di

import com.example.awesomearchsample.core.preferences.AppPreferences
import com.example.awesomearchsample.data.appsettings.AppSettingsRepositoryImpl
import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository

class AppSettingsDataFactory(
    appPreferencesProvider: () -> AppPreferences
) {

    val appSettingsRepository: AppSettingsRepository by lazy {
        AppSettingsRepositoryImpl(
            appPreferences = appPreferencesProvider()
        )
    }
}
