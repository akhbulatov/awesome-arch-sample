package com.example.awesomearchsample.feature.launch

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

internal sealed class LaunchUiEffect : BaseUiEffect {
    data object NavigateToMainHost : LaunchUiEffect()
}
