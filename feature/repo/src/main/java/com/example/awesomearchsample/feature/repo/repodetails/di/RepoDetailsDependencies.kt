package com.example.awesomearchsample.feature.repo.repodetails.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.di.rememberRepoFeatureDependencies

interface RepoDetailsDependencies {
    val getRepoDetailsUseCase: GetRepoDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}

@Composable
fun rememberRepoDetailsDependencies(): RepoDetailsDependencies {
    return rememberRepoFeatureDependencies().repoDetailsDependencies
}
