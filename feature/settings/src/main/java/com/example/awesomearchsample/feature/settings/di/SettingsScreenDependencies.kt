package com.example.awesomearchsample.feature.settings.di

import com.example.awesomearchsample.domain.appsettings.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appsettings.usecase.SetNotificationsEnabledUseCase

interface SettingsScreenDependencies {
    val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase
    val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
}
