package com.example.awesomearchsample.feature.settings.di

import com.example.awesomearchsample.domain.di.DomainFactory

class SettingsFeatureGraph(
    private val domainFactory: DomainFactory
) : SettingsFeatureDependencies {

    override val settingsScreenDependencies: SettingsScreenDependencies by lazy {
        object : SettingsScreenDependencies {
            override val getNotificationsEnabledUseCase =
                domainFactory.appConfigDomainFactory.getNotificationsEnabledUseCase
            override val setNotificationsEnabledUseCase =
                domainFactory.appConfigDomainFactory.setNotificationsEnabledUseCase
        }
    }
}
