package com.example.awesomearchsample.feature.launch.di

import com.example.awesomearchsample.domain.appsettings.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appsettings.usecase.SetIsFirstLaunchUseCase

interface LaunchScreenDependencies {
    val isFirstLaunchUseCase: IsFirstLaunchUseCase
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
}
