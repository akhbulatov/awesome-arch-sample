package com.example.awesomearchsample.feature.repo.api.repodetails

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEvent

sealed class RepoDetailsUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : RepoDetailsUiEvent()
}