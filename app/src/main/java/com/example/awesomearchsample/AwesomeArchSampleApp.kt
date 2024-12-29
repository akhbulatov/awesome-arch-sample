package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.feature.launch.di.LaunchFactory
import com.example.awesomearchsample.feature.launch.di.LaunchFactoryProvider
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsFactory
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsFactoryProvider
import com.example.awesomearchsample.feature.repo.repos.di.ReposFactory
import com.example.awesomearchsample.feature.repo.repos.di.ReposFactoryProvider
import com.example.awesomearchsample.feature.search.di.SearchFactory
import com.example.awesomearchsample.feature.search.di.SearchFactoryProvider
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsFactory
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsFactoryProvider

class AwesomeArchSampleApp : Application(),
    LaunchFactoryProvider,
    ReposFactoryProvider,
    RepoDetailsFactoryProvider,
    SearchFactoryProvider,
    UserDetailsFactoryProvider {

    private val appFactory: AwesomeArchSampleFactory by lazy {
        AwesomeArchSampleFactory(
            context = this
        )
    }

    override val launchFactory: LaunchFactory
        get() = LaunchFactory(
            launchNavigator = appFactory.navigationFactory.launchNavigator,
            domainFactory = appFactory.domainFactory
        )

    override val reposFactory: ReposFactory
        get() = ReposFactory(
            repoNavigator = appFactory.navigationFactory.repoNavigator,
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory,
            commonFeatureFactory = appFactory.commonFeatureFactory
        )
    override val repoDetailsFactory: RepoDetailsFactory
        get() = RepoDetailsFactory(
            repoNavigator = appFactory.navigationFactory.repoNavigator,
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory,
        )

    override val searchFactory: SearchFactory
        get() = SearchFactory(
            searchNavigator = appFactory.navigationFactory.searchNavigator,
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory
        )

    override val userDetailsFactory: UserDetailsFactory
        get() = UserDetailsFactory(
            domainFactory = appFactory.domainFactory,
            coreFactory = appFactory.coreFactory
        )
}
