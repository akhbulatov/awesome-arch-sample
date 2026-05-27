package com.example.awesomearchsample.domain.appsettings.usecase

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow

class IsFirstLaunchUseCase(
    private val appSettingsRepository: AppSettingsRepository
) {

    operator fun invoke(): Flow<Boolean> =
        appSettingsRepository.isFirstLaunch()
}
