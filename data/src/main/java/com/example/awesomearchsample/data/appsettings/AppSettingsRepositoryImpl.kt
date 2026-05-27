package com.example.awesomearchsample.data.appsettings

import com.example.awesomearchsample.core.preferences.AppPreferences
import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow

internal class AppSettingsRepositoryImpl(
    private val appPreferences: AppPreferences
) : AppSettingsRepository {

    override fun isFirstLaunch(): Flow<Boolean> {
        return appPreferences.isFirstLaunch()
    }

    override suspend fun setIsFirstLaunch(value: Boolean) {
        appPreferences.setIsFirstLaunch(value)
    }

    override fun isNotificationsEnabled(): Flow<Boolean> {
        return appPreferences.isNotificationsEnabled()
    }

    override suspend fun setNotificationsEnabled(value: Boolean) {
        appPreferences.setNotificationsEnabled(value)
    }
}
