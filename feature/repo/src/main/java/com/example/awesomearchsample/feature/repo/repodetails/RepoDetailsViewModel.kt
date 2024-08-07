package com.example.awesomearchsample.feature.repo.repodetails

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = RepoDetailsViewModel.Factory::class)
class RepoDetailsViewModel @AssistedInject constructor(
    @Assisted private val repoId: Long,
    private val repoNavigator: RepoNavigator,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<RepoDetailsUiState, BaseUiEffect>(initialUiState = RepoDetailsUiState()) {

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(emptyProgress = true) }
                val repoDetails = getRepoDetailsUseCase.invoke(repoId = repoId)
                mutableUiState.update { uiState.value.copy(repoDetails = repoDetails) }
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.update { it.copy(emptyError = uiError) }
                    }
                )
            } finally {
                mutableUiState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    fun onAuthorClick() {
        uiState.value.repoDetails?.let { repoDetails ->
            viewModelScope.launch {
                mutableUiEffect.send(
                    RepoDetailsUiEffect.NavigateTo(repoNavigator.getUserDetailsScreen(login = repoDetails.author))
                )
            }
        }
    }

    fun onErrorActionClick() {
        mutableUiState.value = RepoDetailsUiState()
        loadRepoDetails()
    }

    @AssistedFactory
    interface Factory {
        fun create(repoId: Long): RepoDetailsViewModel
    }
}