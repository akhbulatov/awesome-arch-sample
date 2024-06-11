package com.example.awesomearchsample.feature.launch

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.common.util.AppLogger
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.apppreferences.usecase.IsFirstLaunchUseCase
import com.example.awesomearchsample.domain.apppreferences.usecase.SetIsFirstLaunchUseCase
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val launchNavigator: LaunchNavigator,
    private val isFirstLaunchUseCase: IsFirstLaunchUseCase,
    private val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase
) : BaseViewModel<Unit, LaunchUiEvent>(initialUiState = Unit) {

    init {
        viewModelScope.launch {
            if (!isFirstLaunchUseCase.invoke().first()) {
                AppLogger.d("FIRST APP LAUNCH!")
                setIsFirstLaunchUseCase.invoke(true)
            }

            mutableUiEvent.send(
                LaunchUiEvent.ResetAll(screen = launchNavigator.getMainHostScreen())
            )
        }
    }
}