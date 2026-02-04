package com.example.awesomearchsample.feature.settings.navigation

import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.settings.SettingsRoute
import com.example.awesomearchsample.feature.settings.advanced.AdvancedSettingsRoute

fun ((NavKey) -> Unit).navigateToSettings() {
    this(SettingsRoute)
}

fun ((NavKey) -> Unit).navigateToAdvancedSettings() {
    this(AdvancedSettingsRoute)
}
