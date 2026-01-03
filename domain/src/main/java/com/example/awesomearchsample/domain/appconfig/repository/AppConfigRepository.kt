package com.example.awesomearchsample.domain.appconfig.repository

import kotlinx.coroutines.flow.Flow

interface AppConfigRepository {
    fun isFirstLaunch(): Flow<Boolean>
    suspend fun setIsFirstLaunch(value: Boolean)
}
