package com.example.awesomearchsample.data.apppreferences

import com.example.awesomearchsample.core.preferences.AppPreferences
import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AppPreferencesRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences
) : AppPreferencesRepository {

    override fun isFirstLaunch(): Flow<Boolean> {
        return appPreferences.isFirstLaunch()
    }

    override suspend fun setIsFirstLaunch(value: Boolean) {
        appPreferences.setIsFirstLaunch(value)
    }
}