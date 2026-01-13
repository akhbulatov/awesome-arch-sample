package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

internal sealed class ReposUiEffect : BaseUiEffect {
    data object NavigateToSearch : ReposUiEffect()
    data class NavigateToRepoDetails(val repoId: Long) : ReposUiEffect()
}
