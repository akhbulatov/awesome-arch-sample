package com.example.awesomearchsample.feature.settings.di

import com.example.awesomearchsample.domain.appconfig.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetNotificationsEnabledUseCase

interface SettingsScreenDependencies {
    val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase
    val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
}
