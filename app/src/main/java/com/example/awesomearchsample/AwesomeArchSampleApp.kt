package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.feature.launch.di.LaunchDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchDependenciesProvider
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependencies
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependenciesProvider
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependenciesProvider
import com.example.awesomearchsample.feature.search.di.SearchDependencies
import com.example.awesomearchsample.feature.search.di.SearchDependenciesProvider
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependenciesProvider

class AwesomeArchSampleApp : Application(),
    LaunchDependenciesProvider,
    ReposDependenciesProvider,
    RepoDetailsDependenciesProvider,
    SearchDependenciesProvider,
    UserDetailsDependenciesProvider {

    private val appFactory: AwesomeArchSampleFactory by lazy {
        AwesomeArchSampleFactory(
            context = this
        )
    }

    override fun getLaunchDependencies(): LaunchDependencies {
        return object : LaunchDependencies {
            override val launchNavigator = appFactory.navigationFactory.launchNavigator
            override val isFirstLaunchUseCase =
                appFactory.domainFactory.appPreferencesDomainFactory.isFirstLaunchUseCase
            override val setIsFirstLaunchUseCase =
                appFactory.domainFactory.appPreferencesDomainFactory.setIsFirstLaunchUseCase
        }
    }

    override fun getReposDependencies(): ReposDependencies {
        return object : ReposDependencies {
            override val repoNavigator = appFactory.navigationFactory.repoNavigator
            override val getReposUseCase = appFactory.domainFactory.repoDomainFactory.getReposUseCase
            override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
            override val analyticsEventSender = appFactory.commonFeatureFactory.analyticsEventSender
        }
    }

    override fun getRepoDetailsDependencies(): RepoDetailsDependencies {
        return object : RepoDetailsDependencies {
            override val repoNavigator = appFactory.navigationFactory.repoNavigator
            override val getRepoDetailsUseCase =
                appFactory.domainFactory.repoDomainFactory.getRepoDetailsUseCase
            override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
        }
    }

    override fun getSearchDependencies(): SearchDependencies {
        return object : SearchDependencies {
            override val searchNavigator = appFactory.navigationFactory.searchNavigator
            override val getSearchResultUseCase =
                appFactory.domainFactory.searchDomainFactory.getSearchResultUseCase
            override val getSearchQueriesUseCase =
                appFactory.domainFactory.searchDomainFactory.getSearchQueriesUseCase
            override val saveSearchQueryUseCase =
                appFactory.domainFactory.searchDomainFactory.saveSearchQueriesUseCase
            override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
            override val resourceManager = appFactory.coreFactory.uiFactory.resourceManager
        }
    }

    override fun getUserDetailsDependencies(): UserDetailsDependencies {
        return object : UserDetailsDependencies {
            override val getUserDetailsUseCase =
                appFactory.domainFactory.userDomainFactory.getUserDetailsUseCase
            override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
        }
    }
}
