package com.example.awesomearchsample.domain.apppreferences.usecase

import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository

class SetIsFirstLaunchUseCase(
    private val appPreferencesRepository: AppPreferencesRepository
) {

    suspend operator fun  invoke(value: Boolean) {
        appPreferencesRepository.setIsFirstLaunch(value)
    }
}