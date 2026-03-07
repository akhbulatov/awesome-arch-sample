package com.example.awesomearchsample.feature.repo.di

import com.example.awesomearchsample.core.ui.di.UiFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.common.di.CommonFeatureFactory
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies

class RepoFeatureGraph(
    private val domainFactory: DomainFactory,
    private val uiFactory: UiFactory,
    private val commonFeatureFactory: CommonFeatureFactory
) : RepoFeatureDependencies {

    override val reposDependencies: ReposDependencies by lazy {
        object : ReposDependencies {
            override val getReposUseCase = domainFactory.repoDomainFactory.getReposUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
            override val analyticsEventSender = commonFeatureFactory.analyticsEventSender
        }
    }

    override val repoDetailsDependencies: RepoDetailsDependencies by lazy {
        object : RepoDetailsDependencies {
            override val getRepoDetailsUseCase = domainFactory.repoDomainFactory.getRepoDetailsUseCase
            override val uiErrorHandler = uiFactory.uiErrorHandler
        }
    }
}
