package com.example.awesomearchsample.feature.launch.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.launch.LaunchScreen
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies

fun EntryProviderScope<NavKey>.addLaunchEntries(
    launchFeatureDependencies: LaunchFeatureDependencies,
    onNavigateToMainHost: () -> Unit
) {
    entry<LaunchRoute> {
        LaunchScreen(
            dependencies = launchFeatureDependencies.launchScreenDependencies,
            onNavigateToMainHost = onNavigateToMainHost
        )
    }
}
