package com.example.awesomearchsample.feature.repo.repos

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

sealed class ReposUiEffect : BaseUiEffect {
    data class NavigateTo(val screen: Screen) : ReposUiEffect()
}