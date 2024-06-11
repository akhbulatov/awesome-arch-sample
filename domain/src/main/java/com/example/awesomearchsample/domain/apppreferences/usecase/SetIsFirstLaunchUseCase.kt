package com.example.awesomearchsample.domain.apppreferences.usecase

import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository
import javax.inject.Inject

class SetIsFirstLaunchUseCase @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {

    suspend operator fun  invoke(value: Boolean) {
        appPreferencesRepository.setIsFirstLaunch(value)
    }
}