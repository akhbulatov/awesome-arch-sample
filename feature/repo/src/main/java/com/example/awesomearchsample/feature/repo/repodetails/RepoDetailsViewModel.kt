package com.example.awesomearchsample.feature.repo.repodetails

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.commonapi.util.launchCatching
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsScreenDependencies

internal class RepoDetailsViewModel(
    private val args: Args,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<RepoDetailsUiState, BaseUiEffect>(initialUiState = RepoDetailsUiState()) {

    data class Args(
        val repoId: Long
    )

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launchCatching(
            onFailure = { e ->
                errorHandler.proceed(e) { uiError ->
                    mutableUiState.value = RepoDetailsUiState(initialError = uiError)
                }
            }
        ) {
            mutableUiState.value = RepoDetailsUiState(isInitialLoading = true)

            val repoDetails = getRepoDetailsUseCase.invoke(repoId = args.repoId)
            mutableUiState.value = RepoDetailsUiState(
                content = RepoDetailsContent(repoDetails = repoDetails)
            )
        }
    }

    fun onAuthorClick() {
        val content = uiState.value.content ?: return
        emitEffect(
            RepoDetailsUiEffect.NavigateToUserDetails(
                login = content.repoDetails.author
            )
        )
    }

    fun onErrorActionClick() {
        loadRepoDetails()
    }

    companion object {
        fun viewModelFactory(
            args: Args,
            dependencies: RepoDetailsScreenDependencies
        ) = viewModelFactory {
            initializer {
                RepoDetailsViewModel(
                    args = args,
                    getRepoDetailsUseCase = dependencies.getRepoDetailsUseCase,
                    errorHandler = dependencies.uiErrorHandler
                )
            }
        }
    }
}
