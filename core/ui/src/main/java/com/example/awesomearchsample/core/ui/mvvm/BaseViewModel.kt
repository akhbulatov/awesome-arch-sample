package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S, E : BaseUiEffect>(initialUiState: S) : ViewModel() {

    protected val mutableUiState = MutableStateFlow(initialUiState)
    val uiState = mutableUiState.asStateFlow()

    protected val mutableUiEffect = MutableSharedFlow<E>(
        replay = 0, // one-shot effects, no replay for new collectors
        extraBufferCapacity = 1 // avoid blocking when collector isn't ready
    )
    val uiEffect = mutableUiEffect.asSharedFlow()

    protected fun emitEffect(effect: E) {
        mutableUiEffect.tryEmit(effect)
    }
}
