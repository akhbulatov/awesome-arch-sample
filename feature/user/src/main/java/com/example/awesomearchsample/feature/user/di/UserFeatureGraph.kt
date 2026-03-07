package com.example.awesomearchsample.feature.user.di

import com.example.awesomearchsample.core.ui.di.UiFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies

class UserFeatureGraph(
    private val domainFactory: DomainFactory,
    private val uiFactory: UiFactory
) : UserFeatureDependencies {

    override val userDetailsDependencies: UserDetailsDependencies by lazy {
        object : UserDetailsDependencies {
            override val getUserDetailsUseCase =
                domainFactory.userDomainFactory.getUserDetailsUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
        }
    }
}
