package com.example.awesomearchsample.feature.repo.api.repodetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : ViewModel() {

    private val screenArg: RepoScreens.RepoDetails = requireNotNull(savedStateHandle[ARG_SCREEN])

    private val _uiState = MutableStateFlow(RepoDetailsUiState())
    val uiState: StateFlow<RepoDetailsUiState> = _uiState

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(emptyProgress = true) }
                val repoDetails = getRepoDetailsUseCase.invoke(repoId = screenArg.repoId)
                _uiState.update { uiState.value.copy(repoDetails = repoDetails) }
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        _uiState.update { it.copy(emptyError = uiError) }
                    }
                )
            } finally {
                _uiState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    fun onErrorActionClick() {
        _uiState.value = RepoDetailsUiState()
        loadRepoDetails()
    }

    companion object {
        const val ARG_SCREEN = "screen"
    }
}