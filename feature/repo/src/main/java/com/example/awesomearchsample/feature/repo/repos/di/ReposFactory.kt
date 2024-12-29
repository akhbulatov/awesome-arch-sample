package com.example.awesomearchsample.feature.repo.repos.di

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.common.di.CommonFeatureFactory
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import com.example.awesomearchsample.feature.repo.repos.ReposViewModel

class ReposFactory(
    private val repoNavigator: RepoNavigator,
    private val domainFactory: DomainFactory,
    private val coreFactory: CoreFactory,
    private val commonFeatureFactory: CommonFeatureFactory
) {

    fun createViewModelFactory() = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return ReposViewModel(
                repoNavigator = repoNavigator,
                getReposUseCase = domainFactory.repoDomainFactory.getReposUseCase,
                errorHandler = coreFactory.uiFactory.uiErrorHandler,
                analyticsEventSender = commonFeatureFactory.analyticsEventSender
            ) as T
        }
    }
}

interface ReposFactoryProvider {
    val reposFactory: ReposFactory
}

@Composable
fun getReposFactory(): ReposFactory {
    val application = (LocalContext.current.applicationContext as Application)
    return (application as ReposFactoryProvider).reposFactory
}