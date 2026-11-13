package com.example.awesomearchsample.di

import android.app.Application
import com.example.awesomearchsample.core.commonapi.app.AppInitializers
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureGraph
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.di.RepoFeatureGraph
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureGraph
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureDependencies
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureGraph
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.di.UserFeatureGraph

class AppGraph(
    application: Application
) : AppDependencies {

    private val appFactory: AwesomeArchSampleFactory by lazy {
        AwesomeArchSampleFactory(
            application = application
        )
    }

    override val launchFeatureDependencies: LaunchFeatureDependencies by lazy {
        LaunchFeatureGraph(
            domainFactory = appFactory.domainFactory
        )
    }

    override val repoFeatureDependencies: RepoFeatureDependencies by lazy {
        RepoFeatureGraph(
            domainFactory = appFactory.domainFactory,
            uiFactory = appFactory.coreFactory.uiFactory,
            commonFeatureFactory = appFactory.commonFeatureFactory
        )
    }

    override val searchFeatureDependencies: SearchFeatureDependencies by lazy {
        SearchFeatureGraph(
            domainFactory = appFactory.domainFactory,
            uiFactory = appFactory.coreFactory.uiFactory
        )
    }

    override val userFeatureDependencies: UserFeatureDependencies by lazy {
        UserFeatureGraph(
            domainFactory = appFactory.domainFactory,
            uiFactory = appFactory.coreFactory.uiFactory
        )
    }

    override val settingsFeatureDependencies: SettingsFeatureDependencies by lazy {
        SettingsFeatureGraph(
            domainFactory = appFactory.domainFactory
        )
    }

    val appInitializers: AppInitializers by lazy {
        appFactory.coreFactory.appInitializers
    }
}
