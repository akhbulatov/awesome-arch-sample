package com.example.awesomearchsample.feature.repo.repos

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEvent

sealed class ReposUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : ReposUiEvent()
}