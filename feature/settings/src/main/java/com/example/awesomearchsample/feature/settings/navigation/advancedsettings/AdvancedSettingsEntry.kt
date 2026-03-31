package com.example.awesomearchsample.feature.settings.navigation.advancedsettings

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.settings.advanced.AdvancedSettingsScreen

internal fun EntryProviderScope<NavKey>.addAdvancedSettingsEntry(
    navigator: AppNavigator
) {
    entry<AdvancedSettingsRoute> {
        AdvancedSettingsScreen(
            onBack = navigator::back
        )
    }
}
