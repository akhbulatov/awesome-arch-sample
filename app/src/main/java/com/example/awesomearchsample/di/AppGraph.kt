package com.example.awesomearchsample.di

import android.content.Context
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureGraph
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.di.RepoFeatureGraph
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureGraph
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.di.UserFeatureGraph

class AppGraph(
    context: Context
) {

    private val appFactory: AwesomeArchSampleFactory by lazy {
        AwesomeArchSampleFactory(
            context = context
        )
    }

    val launchFeatureDependencies: LaunchFeatureDependencies by lazy {
        LaunchFeatureGraph(
            launchNavigator = appFactory.navigationFactory.launchNavigator,
            domainFactory = appFactory.domainFactory
        )
    }

    val repoFeatureDependencies: RepoFeatureDependencies by lazy {
        RepoFeatureGraph(
            repoNavigator = appFactory.navigationFactory.repoNavigator,
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory,
            commonFeatureFactory = appFactory.commonFeatureFactory
        )
    }

    val searchFeatureDependencies: SearchFeatureDependencies by lazy {
        SearchFeatureGraph(
            searchNavigator = appFactory.navigationFactory.searchNavigator,
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory
        )
    }

    val userFeatureDependencies: UserFeatureDependencies by lazy {
        UserFeatureGraph(
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory
        )
    }
}