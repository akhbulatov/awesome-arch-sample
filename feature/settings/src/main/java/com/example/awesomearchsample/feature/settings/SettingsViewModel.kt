package com.example.awesomearchsample.feature.settings

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.appconfig.usecase.GetNotificationsEnabledUseCase
import com.example.awesomearchsample.domain.appconfig.usecase.SetNotificationsEnabledUseCase
import com.example.awesomearchsample.feature.settings.di.SettingsScreenDependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val getNotificationsEnabledUseCase: GetNotificationsEnabledUseCase,
    private val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
) : BaseViewModel<BaseUiEffect>() {

    val uiState: StateFlow<SettingsUiState>
        field = MutableStateFlow(SettingsUiState())

    init {
        viewModelScope.launch {
            getNotificationsEnabledUseCase.invoke().collectLatest { enabled ->
                uiState.value = SettingsUiState(notificationsEnabled = enabled)
            }
        }
    }

    fun onNotificationsToggle() {
        val newValue = !uiState.value.notificationsEnabled
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
