package com.example.awesomearchsample.core.commonfactory.di

import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.commonimpl.error.ErrorHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {

    @Binds
    @Singleton
    abstract fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler
}
