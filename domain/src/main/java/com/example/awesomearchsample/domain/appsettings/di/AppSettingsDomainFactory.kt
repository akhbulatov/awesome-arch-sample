package com.example.awesomearchsample.domain.appsettings.di

import com.example.awesomearchsample.domain.appsettings.repository.AppSettingsRepository
import com.example.awesomearchsample.domain.appsettings.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appsettings.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appsettings.usecase.SetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appsettings.usecase.SetIsFirstLaunchUseCase

class AppSettingsDomainFactory(
    appSettingsRepository: AppSettingsRepository
) {

    val isFirstLaunchUseCase: IsFirstLaunchUseCase = IsFirstLaunchUseCase(
        appSettingsRepository = appSettingsRepository
    )
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase = SetIsFirstLaunchUseCase(
        appSettingsRepository = appSettingsRepository
    )
    val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase = GetNotificationsEnabledUseCase(
        appSettingsRepository = appSettingsRepository
    )
    val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase = SetNotificationsEnabledUseCase(
        appSettingsRepository = appSettingsRepository
    )
}
