package com.example.awesomearchsample.domain.appconfig.usecase

import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsEnabledUseCase(
    private val appConfigRepository: AppConfigRepository
) {
    fun invoke(): Flow<Boolean> {
        return appConfigRepository.isNotificationsEnabled()
    }
}
