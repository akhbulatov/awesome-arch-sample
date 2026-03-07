package com.example.awesomearchsample.feature.user.di

import com.example.awesomearchsample.core.ui.di.UiFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsScreenDependencies

class UserFeatureGraph(
    private val domainFactory: DomainFactory,
    private val uiFactory: UiFactory
) : UserFeatureDependencies {

    override val userDetailsScreenDependencies: UserDetailsScreenDependencies by lazy {
        object : UserDetailsScreenDependencies {
            override val getUserDetailsUseCase =
                domainFactory.userDomainFactory.getUserDetailsUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
        }
    }
}
