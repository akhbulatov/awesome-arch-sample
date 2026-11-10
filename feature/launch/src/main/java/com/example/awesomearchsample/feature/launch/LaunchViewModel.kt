package com.example.awesomearchsample.feature.launch

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.commonapi.util.AppLogger
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.appsettings.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.appsettings.usecase.SetIsFirstLaunchUseCase
import com.example.awesomearchsample.feature.launch.di.LaunchScreenDependencies
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

internal class LaunchViewModel(
    private val isFirstLaunchUseCase: IsFirstLaunchUseCase,
    private val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
) : BaseViewModel<LaunchUiEffect>() {

    init {
        viewModelScope.launch {
            try {
                if (!isFirstLaunchUseCase().first()) {
                    AppLogger.d("First app launch detected")
                    setIsFirstLaunchUseCase(true)
                }
            } catch (e: IOException) {
                AppLogger.e(e)
            }

            emitEffect(LaunchUiEffect.NavigateToMainHost)
        }
    }

    companion object {
        fun viewModelFactory(dependencies: LaunchScreenDependencies) = viewModelFactory {
            initializer {
                LaunchViewModel(
                    isFirstLaunchUseCase = dependencies.isFirstLaunchUseCase,
                    setIsFirstLaunchUseCase = dependencies.setIsFirstLaunchUseCase
                )
            }
        }
    }
}
