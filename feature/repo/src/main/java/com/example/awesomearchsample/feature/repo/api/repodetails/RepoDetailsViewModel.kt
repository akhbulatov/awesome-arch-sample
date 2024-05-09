package com.example.awesomearchsample.feature.repo.api.repodetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<RepoDetailsUiState>(initialUiState = RepoDetailsUiState()) {

    private val screenArg: RepoScreens.RepoDetails = requireNotNull(savedStateHandle[ARG_SCREEN])

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(emptyProgress = true) }
                val repoDetails = getRepoDetailsUseCase.invoke(repoId = screenArg.repoId)
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

    fun onErrorActionClick() {
        mutableUiState.value = RepoDetailsUiState()
        loadRepoDetails()
    }

    companion object {
        const val ARG_SCREEN = "screen"
    }
}