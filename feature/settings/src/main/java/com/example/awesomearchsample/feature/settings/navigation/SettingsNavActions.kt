package com.example.awesomearchsample.feature.settings.navigation

import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.settings.SettingsRoute

fun ((NavKey) -> Unit).navigateToSettings() {
    this(SettingsRoute)
}
