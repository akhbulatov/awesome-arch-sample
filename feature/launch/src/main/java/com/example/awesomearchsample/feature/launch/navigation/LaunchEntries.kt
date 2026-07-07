package com.example.awesomearchsample.feature.launch.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.launch.navigation.launch.addLaunchEntry

fun EntryProviderScope<NavKey>.addLaunchEntries(
    launchFeatureDependencies: LaunchFeatureDependencies,
    onNavigateToMainHost: () -> Unit
) {
    addLaunchEntry(
        launchFeatureDependencies = launchFeatureDependencies,
        onNavigateToMainHost = onNavigateToMainHost
    )
}
