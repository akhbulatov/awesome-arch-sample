package com.example.awesomearchsample.feature.repo.api.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReposUiState())
    val uiState: StateFlow<ReposUiState> = _uiState

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(emptyProgress = true) }
                val repos = getReposUseCase.invoke()
                _uiState.update { uiState.value.copy(repos = repos) }
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
        _uiState.value = ReposUiState() // Задаем исходное состояние стейта
        loadRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        val updatedRepos = uiState.value.repos.updatedByToggleInFavorites(repoBy = repo)
        _uiState.update { it.copy(repos = updatedRepos) }
    }
}