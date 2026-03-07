package com.example.awesomearchsample.feature.repo.repodetails

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.mvvm.onSuccess
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsScreenDependencies
import kotlinx.coroutines.launch

internal class RepoDetailsViewModel(
    private val args: Args,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<RepoDetailsUiState, BaseUiEffect>(initialUiState = RepoDetailsUiState.Idle) {

    data class Args(
        val repoId: Long
    )

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.value = RepoDetailsUiState.Loading
                val repoDetails = getRepoDetailsUseCase.invoke(repoId = args.repoId)
                mutableUiState.value = RepoDetailsUiState.Success(repoDetails = repoDetails)
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.value = RepoDetailsUiState.Error(error = uiError)
                    }
                )
            }
        }
    }

    fun onAuthorClick() {
        uiState.value.onSuccess<RepoDetails> { repoDetails ->
            emitEffect(
                RepoDetailsUiEffect.NavigateToUserDetails(
                    login = repoDetails.author
                )
            )
        }
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
