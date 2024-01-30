package com.example.awesomearchsample.feature.repo.presentation.repos

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.feature.repo.domain.model.Repo

data class ReposUiState(
    val emptyProgress: Boolean = false,
    val emptyError: UiError? = null,
    val repos: List<Repo> = emptyList()
)