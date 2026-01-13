package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.mvvm.SuccessState
import com.example.awesomearchsample.domain.repo.model.Repo

internal sealed class ReposUiState {
    data object Loading : ReposUiState()
    data class Error(val error: UiError) : ReposUiState()
    data class Success(val repos: List<Repo>) : ReposUiState(), SuccessState<List<Repo>> {
        override val data: List<Repo>
            get() = repos
    }
}
