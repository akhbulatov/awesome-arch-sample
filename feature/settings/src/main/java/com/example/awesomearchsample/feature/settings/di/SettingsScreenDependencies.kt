package com.example.awesomearchsample.feature.settings.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.domain.appconfig.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetNotificationsEnabledUseCase

interface SettingsScreenDependencies {
    val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase
    val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
}

@Composable
internal fun rememberSettingsScreenDependencies(): SettingsScreenDependencies {
    return rememberSettingsFeatureDependencies().settingsScreenDependencies
}
