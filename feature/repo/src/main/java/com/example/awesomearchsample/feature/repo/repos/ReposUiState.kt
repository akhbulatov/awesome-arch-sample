package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.model.Repo

data class ReposUiState(
    val emptyProgress: Boolean = false,
    val emptyError: UiError? = null,
    val repos: List<Repo> = emptyList()
)