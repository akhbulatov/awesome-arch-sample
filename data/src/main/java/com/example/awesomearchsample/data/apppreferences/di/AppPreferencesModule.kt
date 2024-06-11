package com.example.awesomearchsample.data.apppreferences.di

import com.example.awesomearchsample.data.apppreferences.AppPreferencesRepositoryImpl
import com.example.awesomearchsample.domain.apppreferences.repository.AppPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppPreferencesModule {

    @Binds
    @Singleton
    abstract fun bindAppPreferencesRepository(impl: AppPreferencesRepositoryImpl): AppPreferencesRepository
}