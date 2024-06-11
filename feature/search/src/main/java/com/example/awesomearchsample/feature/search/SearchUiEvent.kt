package com.example.awesomearchsample.feature.search

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEvent

sealed class SearchUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : SearchUiEvent()
}