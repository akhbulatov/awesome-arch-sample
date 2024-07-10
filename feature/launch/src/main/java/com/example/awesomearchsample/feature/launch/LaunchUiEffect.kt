package com.example.awesomearchsample.feature.launch

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

sealed class LaunchUiEffect : BaseUiEffect {
    data class ResetAll(val screen: Screen) : LaunchUiEffect()
}