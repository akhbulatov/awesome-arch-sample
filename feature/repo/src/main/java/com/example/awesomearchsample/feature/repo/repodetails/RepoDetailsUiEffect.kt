package com.example.awesomearchsample.feature.repo.repodetails

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

sealed class RepoDetailsUiEffect : BaseUiEffect {
    data class NavigateTo(val screen: Screen) : RepoDetailsUiEffect()
}