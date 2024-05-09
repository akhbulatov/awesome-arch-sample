package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T>(initialUiState: T) : ViewModel() {

    protected val mutableUiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<T> = mutableUiState.asStateFlow()
}