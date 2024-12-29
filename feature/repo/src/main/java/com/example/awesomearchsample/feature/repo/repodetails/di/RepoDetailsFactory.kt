package com.example.awesomearchsample.feature.repo.repodetails.di

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsViewModel

class RepoDetailsFactory(
    private val repoNavigator: RepoNavigator,
    private val domainFactory: DomainFactory,
    private val coreFactory: CoreFactory
) {

    fun createViewModelFactory(
        repoId: Long
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return RepoDetailsViewModel(
                repoId = repoId,
                repoNavigator = repoNavigator,
                getRepoDetailsUseCase = domainFactory.repoDomainFactory.getRepoDetailsUseCase,
                errorHandler = coreFactory.uiFactory.uiErrorHandler
            ) as T
        }
    }
}

interface RepoDetailsFactoryProvider {
    val repoDetailsFactory: RepoDetailsFactory
}

@Composable
fun getRepoDetailsFactory(): RepoDetailsFactory {
    val application = (LocalContext.current.applicationContext as Application)
    return (application as RepoDetailsFactoryProvider).repoDetailsFactory
}