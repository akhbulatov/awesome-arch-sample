package com.example.awesomearchsample.domain.appconfig.usecase

import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository

class SetIsFirstLaunchUseCase(
    private val appConfigRepository: AppConfigRepository
) {

    suspend operator fun invoke(value: Boolean) {
        appConfigRepository.setIsFirstLaunch(value)
    }
}
