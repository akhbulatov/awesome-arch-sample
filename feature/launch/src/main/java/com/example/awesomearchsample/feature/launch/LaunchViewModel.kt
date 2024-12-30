package com.example.awesomearchsample.feature.launch

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.common.util.AppLogger
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.apppreferences.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.apppreferences.usecase.SetIsFirstLaunchUseCase
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LaunchViewModel(
    private val launchNavigator: LaunchNavigator,
    private val isFirstLaunchUseCase: IsFirstLaunchUseCase,
    private val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
) : BaseViewModel<Unit, LaunchUiEffect>(initialUiState = Unit) {

    init {
        viewModelScope.launch {
            if (!isFirstLaunchUseCase.invoke().first()) {
                AppLogger.d("FIRST APP LAUNCH!")
                setIsFirstLaunchUseCase.invoke(true)
            }

            mutableUiEffect.send(
                LaunchUiEffect.ResetAll(screen = launchNavigator.getMainHostScreen())
            )
        }
    }
}