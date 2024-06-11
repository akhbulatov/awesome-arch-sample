package com.example.awesomearchsample.di

import com.example.awesomearchsample.LaunchNavigatorImpl
import com.example.awesomearchsample.MainNavigatorImpl
import com.example.awesomearchsample.RepoNavigatorImpl
import com.example.awesomearchsample.SearchNavigatorImpl
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator
import com.example.awesomearchsample.feature.main.navigation.MainNavigator
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    @Singleton
    fun bindLaunchNavigator(impl: LaunchNavigatorImpl): LaunchNavigator

    @Binds
    @Singleton
    fun bindMainNavigator(impl: MainNavigatorImpl): MainNavigator

    @Binds
    @Singleton
    fun bindRepoNavigator(impl: RepoNavigatorImpl): RepoNavigator

    @Binds
    @Singleton
    fun bindSearchNavigator(impl: SearchNavigatorImpl): SearchNavigator
}