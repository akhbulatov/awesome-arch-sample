package com.example.awesomearchsample.domain.appconfig.di

import com.example.awesomearchsample.domain.appconfig.repository.AppConfigRepository
import com.example.awesomearchsample.domain.appconfig.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetIsFirstLaunchUseCase

class AppConfigDomainFactory(
    appConfigRepository: AppConfigRepository
) {

    val isFirstLaunchUseCase: IsFirstLaunchUseCase = IsFirstLaunchUseCase(
        appConfigRepository = appConfigRepository
    )
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase = SetIsFirstLaunchUseCase(
        appConfigRepository = appConfigRepository
    )
    val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase = GetNotificationsEnabledUseCase(
        appConfigRepository = appConfigRepository
    )
    val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase = SetNotificationsEnabledUseCase(
        appConfigRepository = appConfigRepository
    )
}
