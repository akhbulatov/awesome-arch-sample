package com.example.awesomearchsample

import android.content.Context
import com.example.awesomearchsample.feature.launch.di.LaunchDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies
import com.example.awesomearchsample.feature.search.di.SearchDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies

class AppGraph(
    context: Context
) {

    private val appFactory: AwesomeArchSampleFactory by lazy {
        AwesomeArchSampleFactory(
            context = context
        )
    }

    val launchFeatureDependencies: LaunchFeatureDependencies by lazy {
        object : LaunchFeatureDependencies {
            override val launchDependencies = object : LaunchDependencies {
                override val launchNavigator = appFactory.navigationFactory.launchNavigator
                override val isFirstLaunchUseCase =
                    appFactory.domainFactory.appPreferencesDomainFactory.isFirstLaunchUseCase
                override val setIsFirstLaunchUseCase =
                    appFactory.domainFactory.appPreferencesDomainFactory.setIsFirstLaunchUseCase
            }
        }
    }

    val repoFeatureDependencies: RepoFeatureDependencies by lazy {
        object : RepoFeatureDependencies {
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

    val searchFeatureDependencies: SearchFeatureDependencies by lazy {
        object : SearchFeatureDependencies {
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

    val userFeatureDependencies: UserFeatureDependencies by lazy {
        object : UserFeatureDependencies {
            override val userDetailsDependencies = object : UserDetailsDependencies {
                override val getUserDetailsUseCase =
                    appFactory.domainFactory.userDomainFactory.getUserDetailsUseCase
                override val uiErrorHandler = appFactory.coreFactory.uiFactory.uiErrorHandler
            }
        }
    }
}
