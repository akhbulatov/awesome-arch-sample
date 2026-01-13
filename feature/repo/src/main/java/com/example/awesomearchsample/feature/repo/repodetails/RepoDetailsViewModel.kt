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
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependencies
import kotlinx.coroutines.launch

internal class RepoDetailsViewModel(
    private val repoId: Long,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<RepoDetailsUiState, BaseUiEffect>(initialUiState = RepoDetailsUiState.Loading) {

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.value = RepoDetailsUiState.Loading
                val repoDetails = getRepoDetailsUseCase.invoke(repoId = repoId)
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
        fun factory(
            repoId: Long,
            dependencies: RepoDetailsDependencies
        ) = viewModelFactory {
            initializer {
                RepoDetailsViewModel(
                    repoId = repoId,
                    getRepoDetailsUseCase = dependencies.getRepoDetailsUseCase,
                    errorHandler = dependencies.uiErrorHandler
                )
            }
        }
    }
}
