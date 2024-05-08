package com.example.awesomearchsample.di

import com.example.awesomearchsample.LaunchMediatorImpl
import com.example.awesomearchsample.MainMediatorImpl
import com.example.awesomearchsample.feature.launch.navigation.LaunchMediator
import com.example.awesomearchsample.feature.main.navigation.MainMediator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediatorModule {
    @Binds
    @Singleton
    fun bindLaunchMediator(impl: LaunchMediatorImpl): LaunchMediator

    @Binds
    @Singleton
    fun bindMainMediator(impl: MainMediatorImpl): MainMediator
}