package com.example.awesomearchsample.feature.repo.repodetails.di

import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase

interface RepoDetailsScreenDependencies {
    val getRepoDetailsUseCase: GetRepoDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}
