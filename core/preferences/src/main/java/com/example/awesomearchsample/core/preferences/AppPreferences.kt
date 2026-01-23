package com.example.awesomearchsample.core.preferences

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    fun isFirstLaunch(): Flow<Boolean>

    suspend fun setIsFirstLaunch(value: Boolean)
}
