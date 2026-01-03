package com.example.awesomearchsample.feature.launch.di

import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator

class LaunchFeatureGraph(
    private val launchNavigator: LaunchNavigator,
    private val domainFactory: DomainFactory
) : LaunchFeatureDependencies {

    override val launchDependencies: LaunchDependencies by lazy {
        object : LaunchDependencies {
            override val launchNavigator = this@LaunchFeatureGraph.launchNavigator
            override val isFirstLaunchUseCase =
                domainFactory.appConfigDomainFactory.isFirstLaunchUseCase
            override val setIsFirstLaunchUseCase =
                domainFactory.appConfigDomainFactory.setIsFirstLaunchUseCase
        }
    }
}
