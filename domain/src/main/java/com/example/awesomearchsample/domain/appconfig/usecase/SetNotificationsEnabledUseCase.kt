package com.example.awesomearchsample.domain.appconfig.usecase

import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository

class SetNotificationsEnabledUseCase(
    private val appConfigRepository: AppConfigRepository
) {
    suspend fun invoke(value: Boolean) {
        appConfigRepository.setNotificationsEnabled(value)
    }
}
