package com.example.awesomearchsample.data.appconfig

import com.example.awesomearchsample.core.preferences.AppPreferences
import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository
import kotlinx.coroutines.flow.Flow

internal class AppConfigRepositoryImpl(
    private val appPreferences: AppPreferences
) : AppConfigRepository {

    override fun isFirstLaunch(): Flow<Boolean> {
        return appPreferences.isFirstLaunch()
    }

    override suspend fun setIsFirstLaunch(value: Boolean) {
        appPreferences.setIsFirstLaunch(value)
    }
}
