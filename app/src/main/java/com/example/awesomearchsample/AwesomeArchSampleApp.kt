package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.feature.launch.di.LaunchDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependenciesProvider
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependenciesProvider
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies
import com.example.awesomearchsample.feature.search.di.SearchDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependenciesProvider
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.di.UserFeatureDependenciesProvider
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies

class AwesomeArchSampleApp : Application(),
    LaunchFeatureDependenciesProvider,
    RepoFeatureDependenciesProvider,
    SearchFeatureDependenciesProvider,
    UserFeatureDependenciesProvider {

    private val appFactory: AwesomeArchSampleFactory by lazy {
        AwesomeArchSampleFactory(
            context = this
        )
    }

    override fun getLaunchFeatureDependencies(): LaunchFeatureDependencies {
        return object : LaunchFeatureDependencies {
            override val launchDependencies = object : LaunchDependencies {
                override val launchNavigator = appFactory.navigationFactory.launchNavigator
                override val isFirstLaunchUseCase =
                    appFactory.domainFactory.appPreferencesDomainFactory.isFirstLaunchUseCase
                override val setIsFirstLaunchUseCase =
                    appFactory.domainFactory.appPreferencesDomainFactory.setIsFirstLaunchUseCase
            }
        }
    }

    override fun getRepoFeatureDependencies(): RepoFeatureDependencies {
        return object : RepoFeatureDependencies {
            override val reposDependencies = object : ReposDependencies {
                override val repoNavigator = appFactory.navigationFactory.repoNavigator
                override val getReposUseCase =
                    appFactory.domainFactory.repoDomainFactory.getReposUseCase
                override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
                override val analyticsEventSender =
                    appFactory.commonFeatureFactory.analyticsEventSender
            }
            override val repoDetailsDependencies = object : RepoDetailsDependencies {
                override val repoNavigator = appFactory.navigationFactory.repoNavigator
                override val getRepoDetailsUseCase =
                    appFactory.domainFactory.repoDomainFactory.getRepoDetailsUseCase
                override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
            }
        }
    }

    override fun getSearchFeatureDependencies(): SearchFeatureDependencies {
        return object : SearchFeatureDependencies {
            override val searchDependencies = object : SearchDependencies {
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
    }

    override fun getUserFeatureDependencies(): UserFeatureDependencies {
        return object : UserFeatureDependencies {
            override val userDetailsDependencies = object : UserDetailsDependencies {
                override val getUserDetailsUseCase =
                    appFactory.domainFactory.userDomainFactory.getUserDetailsUseCase
                override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
            }
        }
    }
}
