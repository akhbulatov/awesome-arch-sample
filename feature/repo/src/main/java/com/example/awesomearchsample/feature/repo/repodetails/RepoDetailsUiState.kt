package com.example.awesomearchsample.feature.repo.repodetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.RepoDetails

internal data class RepoDetailsUiState(
    val isInitialLoading: Boolean = false,
    val initialError: UiError? = null,
    val content: RepoDetailsContent? = null
)

internal data class RepoDetailsContent(
    val repoDetails: RepoDetails
)
