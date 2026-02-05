package com.example.awesomearchsample.feature.main.navigation

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.main.host.MainHostRoute

fun AppNavigator.navigateToMainHost() {
    resetTo(MainHostRoute)
}
