package com.example.awesomearchsample.feature.launch

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEvent

sealed class LaunchUiEvent : BaseUiEvent {
    data class ResetAll(val screen: Screen) : LaunchUiEvent()
}