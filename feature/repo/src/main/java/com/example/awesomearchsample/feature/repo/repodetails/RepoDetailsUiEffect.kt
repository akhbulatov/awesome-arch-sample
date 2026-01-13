package com.example.awesomearchsample.feature.repo.repodetails

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

internal sealed class RepoDetailsUiEffect : BaseUiEffect {
    data class NavigateToUserDetails(val login: String) : RepoDetailsUiEffect()
}
