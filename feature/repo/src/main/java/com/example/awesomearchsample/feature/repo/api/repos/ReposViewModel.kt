package com.example.awesomearchsample.feature.repo.api.repos

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.aartikov.alligator.Navigator
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<ReposUiState, ReposUiEvent>(initialUiState = ReposUiState()) {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(emptyProgress = true) }
                val repos = getReposUseCase.invoke()
                mutableUiState.update { uiState.value.copy(repos = repos) }
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
        mutableUiState.value = ReposUiState()
        loadRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        val updatedRepos = uiState.value.repos.updatedByToggleInFavorites(repoBy = repo)
        mutableUiState.update { it.copy(repos = updatedRepos) }
    }

    fun onRepoClick(repo: Repo) {
        navigator.goForward(RepoScreens.RepoDetails(repoId = repo.id))
    }
}