package com.example.awesomearchsample.feature.repo.api.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler
) : ViewModel() {

    private val _uiState = MutableLiveData(ReposUiState())
    val uiState: LiveData<ReposUiState> = _uiState

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                _uiState.value = uiState.value?.copy(emptyProgress = true)
                val repos = getReposUseCase.invoke()
                _uiState.value = uiState.value?.copy(repos = repos)
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { _uiState.value = uiState.value?.copy(emptyError = it) }
                )
            } finally {
                _uiState.value = uiState.value?.copy(emptyProgress = false)
            }
        }
    }

    fun onErrorActionClick() {
        _uiState.value = ReposUiState() // Задаем исходное состояние стейта
        loadRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        val uiState = uiState.value ?: return
        val updatedRepos = uiState.repos.updatedByToggleInFavorites(repoBy = repo)
        _uiState.value = uiState.copy(repos = updatedRepos)
    }
}