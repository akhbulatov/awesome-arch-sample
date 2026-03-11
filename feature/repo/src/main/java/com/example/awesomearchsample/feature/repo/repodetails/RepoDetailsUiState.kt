package com.example.awesomearchsample.feature.repo.repodetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.RepoDetails

internal sealed class RepoDetailsUiState {
    data object Initial : RepoDetailsUiState()
    data object Loading : RepoDetailsUiState()
    data class Error(val error: UiError) : RepoDetailsUiState()
    data class Success(val repoDetails: RepoDetails) : RepoDetailsUiState()
}
