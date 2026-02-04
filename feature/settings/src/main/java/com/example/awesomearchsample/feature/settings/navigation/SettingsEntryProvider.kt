package com.example.awesomearchsample.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.settings.SettingsRoute
import com.example.awesomearchsample.feature.settings.SettingsScreen
import com.example.awesomearchsample.feature.settings.advanced.AdvancedSettingsRoute
import com.example.awesomearchsample.feature.settings.advanced.AdvancedSettingsScreen

fun EntryProviderScope<NavKey>.addSettingsEntries(
    navigate: (NavKey) -> Unit,
    onBack: () -> Unit
) {
    entry<SettingsRoute> {
        SettingsScreen(
            onBack = onBack,
            onNavigateToAdvancedSettings = navigate::navigateToAdvancedSettings
        )
    }
    entry<AdvancedSettingsRoute> {
        AdvancedSettingsScreen(
            onBack = onBack
        )
    }
}
