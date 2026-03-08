package com.example.awesomearchsample.feature.launch.di

import com.example.awesomearchsample.domain.appconfig.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetIsFirstLaunchUseCase

interface LaunchScreenDependencies {
    val isFirstLaunchUseCase: IsFirstLaunchUseCase
    val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
}
