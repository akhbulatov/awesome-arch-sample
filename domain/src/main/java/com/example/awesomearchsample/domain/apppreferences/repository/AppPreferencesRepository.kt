package com.example.awesomearchsample.domain.apppreferences.repository

import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {
    fun isFirstLaunch(): Flow<Boolean>
    suspend fun setIsFirstLaunch(value: Boolean)
}