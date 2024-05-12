package com.example.awesomearchsample.feature.repo.api.repodetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEvent
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.feature.repo.api.navigation.RepoMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.aartikov.alligator.Navigator
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val repoMediator: RepoMediator,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<RepoDetailsUiState, BaseUiEvent>(initialUiState = RepoDetailsUiState()) {

    private val repoIdArg: Long = requireNotNull(savedStateHandle[ARG_REPO_ID])

    init {
        loadRepoDetails()
    }

    private fun loadRepoDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(emptyProgress = true) }
                val repoDetails = getRepoDetailsUseCase.invoke(repoId = repoIdArg)
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
            navigator.goForward(repoMediator.getUserDetailsScreen(login = repoDetails.author))
        }
    }

    fun onErrorActionClick() {
        mutableUiState.value = RepoDetailsUiState()
        loadRepoDetails()
    }

    companion object {
        private const val ARG_REPO_ID = "repo_id"
    }
}