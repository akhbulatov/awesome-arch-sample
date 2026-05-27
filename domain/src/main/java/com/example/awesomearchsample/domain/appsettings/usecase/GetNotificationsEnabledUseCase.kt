package com.example.awesomearchsample.domain.appsettings.usecase

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsEnabledUseCase(
    private val appSettingsRepository: AppSettingsRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return appSettingsRepository.isNotificationsEnabled()
    }
}
