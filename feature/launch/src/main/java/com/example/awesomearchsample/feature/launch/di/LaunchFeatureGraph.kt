package com.example.awesomearchsample.feature.launch.di

import com.example.awesomearchsample.domain.di.DomainFactory

class LaunchFeatureGraph(
    private val domainFactory: DomainFactory
) : LaunchFeatureDependencies {

    override val launchDependencies: LaunchDependencies by lazy {
        object : LaunchDependencies {
            override val isFirstLaunchUseCase =
                domainFactory.appConfigDomainFactory.isFirstLaunchUseCase
            override val setIsFirstLaunchUseCase =
                domainFactory.appConfigDomainFactory.setIsFirstLaunchUseCase
        }
    }
}
