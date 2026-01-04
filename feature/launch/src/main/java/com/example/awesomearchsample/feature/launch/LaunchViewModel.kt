package com.example.awesomearchsample.feature.launch

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.common.util.AppLogger
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.appconfig.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetIsFirstLaunchUseCase
import com.example.awesomearchsample.feature.launch.di.LaunchDependencies
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

            emitEffect(LaunchUiEffect.ResetAll(screen = launchNavigator.getMainHostScreen()))
        }
    }

    companion object {
        fun factory(dependencies: LaunchDependencies) = viewModelFactory {
            initializer {
                LaunchViewModel(
                    launchNavigator = dependencies.launchNavigator,
                    isFirstLaunchUseCase = dependencies.isFirstLaunchUseCase,
                    setIsFirstLaunchUseCase = dependencies.setIsFirstLaunchUseCase
                )
            }
        }
    }
}
