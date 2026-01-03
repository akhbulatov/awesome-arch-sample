package com.example.awesomearchsample.feature.repo.repodetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.RepoDetails

sealed class RepoDetailsUiState {
    data object Loading : RepoDetailsUiState()
    data class Error(val error: UiError) : RepoDetailsUiState()
    data class Content(val repoDetails: RepoDetails) : RepoDetailsUiState()
}
