package com.example.awesomearchsample.feature.repo.repos.di

import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender

interface ReposScreenDependencies {
    val getReposUseCase: GetReposUseCase
    val uiErrorHandler: UiErrorHandler
    val analyticsEventSender: AnalyticsEventSender
}
