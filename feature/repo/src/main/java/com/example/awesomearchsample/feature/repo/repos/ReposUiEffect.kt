package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.text.UiText

internal sealed class ReposUiEffect : BaseUiEffect {
    data object NavigateToSearch : ReposUiEffect()
    data object NavigateToSettings : ReposUiEffect()
    data class NavigateToRepoDetails(val repoId: Long) : ReposUiEffect()
    data class ShowErrorMessage(val message: UiText) : ReposUiEffect()
}
