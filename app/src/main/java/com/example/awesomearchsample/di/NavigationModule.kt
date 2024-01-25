package com.example.awesomearchsample.di

import com.example.awesomearchsample.AppRegistryNavigationFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.NavigationFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    private val androidNavigator = AndroidNavigator(AppRegistryNavigationFactory())

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = androidNavigator

    @Provides
    @Singleton
    fun provideNavigationFactory(): NavigationFactory = androidNavigator.navigationFactory

    @Provides
    @Singleton
    fun provideNavigationContextBinder(): NavigationContextBinder = androidNavigator

    @Provides
    @Singleton
    fun provideScreenResolver(): ScreenResolver = androidNavigator.screenResolver
}
