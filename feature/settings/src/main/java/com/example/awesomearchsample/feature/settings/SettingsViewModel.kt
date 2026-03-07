package com.example.awesomearchsample.feature.settings

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.appconfig.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetNotificationsEnabledUseCase
import com.example.awesomearchsample.feature.settings.di.SettingsScreenDependencies
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase,
    private val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
) : BaseViewModel<SettingsUiState, BaseUiEffect>(
    initialUiState = SettingsUiState()
) {

    init {
        viewModelScope.launch {
            getNotificationsEnabledUseCase.invoke().collectLatest { enabled ->
                mutableUiState.value = SettingsUiState(notificationsEnabled = enabled)
            }
        }
    }

    fun onNotificationsToggle() {
        val newValue = !mutableUiState.value.notificationsEnabled
        viewModelScope.launch {
            setNotificationsEnabledUseCase.invoke(newValue)
        }
    }

    companion object {
        fun viewModelFactory(dependencies: SettingsScreenDependencies) = viewModelFactory {
            initializer {
                SettingsViewModel(
                    getNotificationsEnabledUseCase = dependencies.getNotificationsEnabledUseCase,
                    setNotificationsEnabledUseCase = dependencies.setNotificationsEnabledUseCase
                )
            }
        }
    }
}
