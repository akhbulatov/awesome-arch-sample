package com.example.awesomearchsample.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.settings.SettingsRoute
import com.example.awesomearchsample.feature.settings.SettingsScreen

fun EntryProviderScope<NavKey>.addSettingsEntries(
    onBack: () -> Unit
) {
    entry<SettingsRoute> {
        SettingsScreen(
            onBack = onBack
        )
    }
}
