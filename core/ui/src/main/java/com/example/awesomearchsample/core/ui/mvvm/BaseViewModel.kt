package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S, E : BaseUiEffect>(initialUiState: S) : ViewModel() {

    protected val mutableUiState = MutableStateFlow(initialUiState)
    val uiState = mutableUiState.asStateFlow()

    protected val mutableUiEffect = Channel<E>()
    val uiEffect = mutableUiEffect.receiveAsFlow()
}