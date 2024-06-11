package com.example.awesomearchsample.domain.apppreferences.usecase

import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFirstLaunchUseCase @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {

    fun invoke(): Flow<Boolean> =
        appPreferencesRepository.isFirstLaunch()
}