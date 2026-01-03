package com.example.awesomearchsample.feature.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.common.util.AppLogger
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.apppreferences.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.apppreferences.usecase.SetIsFirstLaunchUseCase
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

            mutableUiEffect.send(
                LaunchUiEffect.ResetAll(screen = launchNavigator.getMainHostScreen())
            )
        }
    }

    companion object {
        fun factory(dependencies: LaunchDependencies) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return LaunchViewModel(
                    launchNavigator = dependencies.launchNavigator,
                    isFirstLaunchUseCase = dependencies.isFirstLaunchUseCase,
                    setIsFirstLaunchUseCase = dependencies.setIsFirstLaunchUseCase
                ) as T
            }
        }
    }
}
