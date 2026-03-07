package com.example.awesomearchsample.feature.settings.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.util.getApplicationInstance

interface SettingsFeatureDependencies {
    val settingsScreenDependencies: SettingsScreenDependencies
}

interface SettingsFeatureDependenciesProvider {
    fun getSettingsFeatureDependencies(): SettingsFeatureDependencies
}

@Composable
internal fun rememberSettingsFeatureDependencies(): SettingsFeatureDependencies {
    val application = getApplicationInstance()
    return (application as SettingsFeatureDependenciesProvider).getSettingsFeatureDependencies()
}
