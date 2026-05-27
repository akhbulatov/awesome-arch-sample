package com.example.awesomearchsample.domain.appsettings.usecase

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository

class SetIsFirstLaunchUseCase(
    private val appSettingsRepository: AppSettingsRepository
) {

    suspend operator fun invoke(value: Boolean) {
        appSettingsRepository.setIsFirstLaunch(value)
    }
}
