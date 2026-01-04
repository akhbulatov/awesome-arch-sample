package com.example.awesomearchsample.feature.repo.repodetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.mvvm.SuccessState
import com.example.awesomearchsample.domain.repo.model.RepoDetails

sealed class RepoDetailsUiState {
    data object Loading : RepoDetailsUiState()
    data class Error(val error: UiError) : RepoDetailsUiState()
    data class Success(val repoDetails: RepoDetails) : RepoDetailsUiState(), SuccessState<RepoDetails> {
        override val data: RepoDetails
            get() = repoDetails
    }
}
