package com.example.awesomearchsample.domain.appsettings.usecase

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository

class SetNotificationsEnabledUseCase(
    private val appSettingsRepository: AppSettingsRepository
) {
    suspend operator fun invoke(value: Boolean) {
        appSettingsRepository.setNotificationsEnabled(value)
    }
}
