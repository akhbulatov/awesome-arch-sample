package com.example.awesomearchsample.domain.apppreferences.di

import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository
import com.example.awesomearchsample.domain.apppreferences.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.apppreferences.usecase.SetIsFirstLaunchUseCase

class AppPreferencesDomainFactory(
    appPreferencesRepository: AppPreferencesRepository
) {

    val isFirstLaunchUseCase: IsFirstLaunchUseCase = IsFirstLaunchUseCase(
        appPreferencesRepository = appPreferencesRepository
    )
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase = SetIsFirstLaunchUseCase(
        appPreferencesRepository = appPreferencesRepository
    )
}