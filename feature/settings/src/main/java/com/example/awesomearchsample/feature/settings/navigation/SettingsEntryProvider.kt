package com.example.awesomearchsample.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureDependencies
import com.example.awesomearchsample.feature.settings.navigation.advancedsettings.addAdvancedSettingsEntry
import com.example.awesomearchsample.feature.settings.navigation.settings.addSettingsEntry

fun EntryProviderScope<NavKey>.addSettingsEntries(
    settingsFeatureDependencies: SettingsFeatureDependencies,
    navigator: AppNavigator
) {
    addSettingsEntry(
        settingsFeatureDependencies = settingsFeatureDependencies,
        navigator = navigator
    )
    addAdvancedSettingsEntry(
        navigator = navigator
    )
}
