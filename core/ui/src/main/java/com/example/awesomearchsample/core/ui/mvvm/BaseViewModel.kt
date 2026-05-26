package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E : BaseUiEffect>(initialUiState: S) : ViewModel() {

    protected val mutableUiState = MutableStateFlow(initialUiState)
    val uiState = mutableUiState.asStateFlow()

    private val mutableUiEffect = Channel<E>(capacity = Channel.BUFFERED)
    val uiEffect = mutableUiEffect.receiveAsFlow()

    protected fun emitEffect(effect: E) {
        viewModelScope.launch {
            mutableUiEffect.send(effect)
        }
    }
}
