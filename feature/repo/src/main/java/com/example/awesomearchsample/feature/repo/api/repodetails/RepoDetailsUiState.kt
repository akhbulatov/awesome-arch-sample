package com.example.awesomearchsample.feature.repo.api.repodetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.RepoDetails

data class RepoDetailsUiState(
    val emptyProgress: Boolean = false,
    val emptyError: UiError? = null,
    val repoDetails: RepoDetails? = null
)