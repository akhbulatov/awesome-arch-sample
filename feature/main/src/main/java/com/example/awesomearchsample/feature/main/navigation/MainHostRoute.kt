package com.example.awesomearchsample.feature.main.navigation

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data object MainHostRoute : NavRoute

fun AppNavigator.navigateToMainHost() {
    resetTo(MainHostRoute)
}
