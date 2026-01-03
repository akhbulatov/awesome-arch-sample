package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.Repo

sealed class ReposUiState {
    data object Loading : ReposUiState()
    data class Error(val error: UiError) : ReposUiState()
    data class Content(val repos: List<Repo>) : ReposUiState()
}
