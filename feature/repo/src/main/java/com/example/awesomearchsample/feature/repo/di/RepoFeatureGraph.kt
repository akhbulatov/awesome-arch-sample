package com.example.awesomearchsample.feature.repo.di

import com.example.awesomearchsample.core.ui.di.UiFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.common.di.CommonFeatureFactory
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsScreenDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies

class RepoFeatureGraph(
    private val domainFactory: DomainFactory,
    private val uiFactory: UiFactory,
    private val commonFeatureFactory: CommonFeatureFactory
) : RepoFeatureDependencies {

    override val reposScreenDependencies: ReposScreenDependencies by lazy {
        object : ReposScreenDependencies {
            override val getReposUseCase = domainFactory.repoDomainFactory.getReposUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
            override val analyticsEventSender = commonFeatureFactory.analyticsEventSender
        }
    }

    override val repoDetailsScreenDependencies: RepoDetailsScreenDependencies by lazy {
        object : RepoDetailsScreenDependencies {
            override val getRepoDetailsUseCase = domainFactory.repoDomainFactory.getRepoDetailsUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
        }
    }
}
