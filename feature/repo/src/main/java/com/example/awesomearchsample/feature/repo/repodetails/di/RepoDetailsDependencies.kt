package com.example.awesomearchsample.feature.repo.repodetails.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.di.rememberRepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator

interface RepoDetailsDependencies {
    val repoNavigator: RepoNavigator
    val getRepoDetailsUseCase: GetRepoDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}

@Composable
fun rememberRepoDetailsDependencies(): RepoDetailsDependencies {
    return rememberRepoFeatureDependencies().repoDetailsDependencies
}
