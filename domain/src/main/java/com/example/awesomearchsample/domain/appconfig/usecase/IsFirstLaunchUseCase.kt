package com.example.awesomearchsample.domain.appconfig.usecase

import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository
import kotlinx.coroutines.flow.Flow

class IsFirstLaunchUseCase(
    private val appConfigRepository: AppConfigRepository
) {

    fun invoke(): Flow<Boolean> =
        appConfigRepository.isFirstLaunch()
}
