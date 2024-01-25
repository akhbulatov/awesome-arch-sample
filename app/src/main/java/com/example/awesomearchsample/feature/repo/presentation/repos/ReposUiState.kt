package com.example.awesomearchsample.feature.repo.presentation.repos

import com.example.awesomearchsample.feature.repo.domain.model.Repo

data class ReposUiState(
    val repos: List<Repo> = emptyList()
)