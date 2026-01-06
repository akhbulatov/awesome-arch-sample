package com.example.awesomearchsample.feature.launch

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

sealed class LaunchUiEffect : BaseUiEffect {
    data object NavigateToMainHost : LaunchUiEffect()
}
