package com.example.awesomearchsample.feature.settings.navigation.advancedsettings

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
internal object AdvancedSettingsRoute : NavRoute

internal fun AppNavigator.navigateToAdvancedSettings() {
    navigateTo(AdvancedSettingsRoute)
}
