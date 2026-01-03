package com.example.awesomearchsample.feature.launch.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.awesomearchsample.core.ui.util.getApplicationInstance

interface LaunchFeatureDependencies {
    val launchDependencies: LaunchDependencies
}

interface LaunchFeatureDependenciesProvider {
    fun getLaunchFeatureDependencies(): LaunchFeatureDependencies
}

@Composable
fun rememberLaunchFeatureDependencies(): LaunchFeatureDependencies {
    val application = getApplicationInstance()
    return remember { (application as LaunchFeatureDependenciesProvider).getLaunchFeatureDependencies() }
}
