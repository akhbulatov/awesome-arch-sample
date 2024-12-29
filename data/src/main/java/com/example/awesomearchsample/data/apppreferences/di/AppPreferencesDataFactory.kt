package com.example.awesomearchsample.data.apppreferences.di

import com.example.awesomearchsample.core.commonfactory.di.CoreFactory
import com.example.awesomearchsample.data.apppreferences.AppPreferencesRepositoryImpl
import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository

class AppPreferencesDataFactory(
    private val coreFactory: CoreFactory
) {

    val appPreferencesRepository: AppPreferencesRepository by lazy {
        AppPreferencesRepositoryImpl(
            appPreferences = coreFactory.preferencesFactory.appPreferences
        )
    }
}