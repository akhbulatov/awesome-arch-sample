package com.example.awesomearchsample.feature.launch.di

import com.example.awesomearchsample.domain.di.DomainFactory

class LaunchFeatureGraph(
    private val domainFactory: DomainFactory
) : LaunchFeatureDependencies {

    override val launchScreenDependencies: LaunchScreenDependencies by lazy {
        object : LaunchScreenDependencies {
            override val isFirstLaunchUseCase =
                domainFactory.appSettingsDomainFactory.isFirstLaunchUseCase
            override val setIsFirstLaunchUseCase =
                domainFactory.appSettingsDomainFactory.setIsFirstLaunchUseCase
        }
    }
}
