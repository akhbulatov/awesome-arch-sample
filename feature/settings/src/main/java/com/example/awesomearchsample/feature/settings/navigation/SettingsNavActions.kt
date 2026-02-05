package com.example.awesomearchsample.feature.settings.navigation

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.settings.SettingsRoute
import com.example.awesomearchsample.feature.settings.advanced.AdvancedSettingsRoute

fun AppNavigator.navigateToSettings() {
    navigateTo(SettingsRoute)
}

internal fun AppNavigator.navigateToAdvancedSettings() {
    navigateTo(AdvancedSettingsRoute)
}
