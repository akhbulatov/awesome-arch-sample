package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S, E : BaseUiEvent>(initialUiState: S) : ViewModel() {

    protected val mutableUiState = MutableStateFlow(initialUiState)
    val uiState = mutableUiState.asStateFlow()

    protected val mutableUiEvent = Channel<E>()
    val uiEvent = mutableUiEvent.receiveAsFlow()
}