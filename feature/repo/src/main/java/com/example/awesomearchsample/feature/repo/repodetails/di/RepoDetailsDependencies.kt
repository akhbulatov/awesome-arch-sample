package com.example.awesomearchsample.feature.repo.repodetails.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator

interface RepoDetailsDependencies {
    val repoNavigator: RepoNavigator
    val getRepoDetailsUseCase: GetRepoDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}

interface RepoDetailsDependenciesProvider {
    fun getRepoDetailsDependencies(): RepoDetailsDependencies
}

@Composable
fun rememberRepoDetailsDependencies(): RepoDetailsDependencies {
    val application = getApplicationInstance()
    return (application as RepoDetailsDependenciesProvider).getRepoDetailsDependencies()
}
