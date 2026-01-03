package com.example.awesomearchsample.feature.launch.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.domain.appconfig.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetIsFirstLaunchUseCase
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator

interface LaunchDependencies {
    val launchNavigator: LaunchNavigator
    val isFirstLaunchUseCase: IsFirstLaunchUseCase
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
}

@Composable
fun rememberLaunchDependencies(): LaunchDependencies {
    return rememberLaunchFeatureDependencies().launchDependencies
}
