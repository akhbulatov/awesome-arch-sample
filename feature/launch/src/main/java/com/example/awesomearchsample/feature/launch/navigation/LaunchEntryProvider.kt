package com.example.awesomearchsample.feature.launch.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.launch.LaunchRoute
import com.example.awesomearchsample.feature.launch.LaunchScreen

fun EntryProviderScope<NavKey>.addLaunchEntries(
    onNavigateToMainHost: () -> Unit
) {
    entry<LaunchRoute> {
        LaunchScreen(onNavigateToMainHost = onNavigateToMainHost)
    }
}
