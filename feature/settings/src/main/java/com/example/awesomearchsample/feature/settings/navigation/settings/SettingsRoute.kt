package com.example.awesomearchsample.feature.settings.navigation.settings

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
internal object SettingsRoute : NavRoute

fun AppNavigator.navigateToSettings() {
    navigateTo(SettingsRoute)
}
