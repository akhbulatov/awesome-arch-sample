package com.example.awesomearchsample.feature.launch.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.domain.apppreferences.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.apppreferences.usecase.SetIsFirstLaunchUseCase
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator

interface LaunchDependencies {
    val launchNavigator: LaunchNavigator
    val isFirstLaunchUseCase: IsFirstLaunchUseCase
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
}

interface LaunchDependenciesProvider {
    fun getLaunchDependencies(): LaunchDependencies
}

@Composable
fun rememberLaunchDependencies(): LaunchDependencies {
    val application = getApplicationInstance()
    return remember { (application as LaunchDependenciesProvider).getLaunchDependencies() }
}
