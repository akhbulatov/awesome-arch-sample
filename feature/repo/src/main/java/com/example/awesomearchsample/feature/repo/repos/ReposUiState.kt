package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.Repo

internal data class ReposUiState(
    val isInitialLoading: Boolean = false,
    val initialError: UiError? = null,
    val initialEmptyData: UiEmptyData? = null,
    val content: ReposContent = ReposContent(),
    val isRefreshing: Boolean = false
)

internal data class ReposContent(
    val repos: List<Repo> = emptyList()
)
