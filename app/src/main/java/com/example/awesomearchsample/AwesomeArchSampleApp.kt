package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependenciesProvider
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependenciesProvider
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependenciesProvider
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.di.UserFeatureDependenciesProvider

class AwesomeArchSampleApp : Application(),
    LaunchFeatureDependenciesProvider,
    RepoFeatureDependenciesProvider,
    SearchFeatureDependenciesProvider,
    UserFeatureDependenciesProvider {

    private val graph: AppGraph by lazy { AppGraph(this) }

    override fun getLaunchFeatureDependencies(): LaunchFeatureDependencies =
        graph.launchFeatureDependencies

    override fun getRepoFeatureDependencies(): RepoFeatureDependencies =
        graph.repoFeatureDependencies

    override fun getSearchFeatureDependencies(): SearchFeatureDependencies =
        graph.searchFeatureDependencies

    override fun getUserFeatureDependencies(): UserFeatureDependencies =
        graph.userFeatureDependencies
}
