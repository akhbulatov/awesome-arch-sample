package com.example.awesomearchsample.domain.appsettings.repository

import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    fun isFirstLaunch(): Flow<Boolean>
    suspend fun setIsFirstLaunch(value: Boolean)

    fun isNotificationsEnabled(): Flow<Boolean>
    suspend fun setNotificationsEnabled(value: Boolean)
}
