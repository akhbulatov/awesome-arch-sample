package com.example.awesomearchsample.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureDependencies
import com.example.awesomearchsample.feature.settings.SettingsScreen
import com.example.awesomearchsample.feature.settings.advanced.AdvancedSettingsScreen

fun EntryProviderScope<NavKey>.addSettingsEntries(
    settingsFeatureDependencies: SettingsFeatureDependencies,
    navigator: AppNavigator
) {
    entry<SettingsRoute> {
        SettingsScreen(
            dependencies = settingsFeatureDependencies.settingsScreenDependencies,
            onBack = navigator::back,
            onNavigateToAdvancedSettings = navigator::navigateToAdvancedSettings
        )
    }
    entry<AdvancedSettingsRoute> {
        AdvancedSettingsScreen(
            onBack = navigator::back
        )
    }
}
