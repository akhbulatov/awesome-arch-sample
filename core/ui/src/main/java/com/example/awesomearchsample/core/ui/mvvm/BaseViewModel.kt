package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
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

    protected inline fun <reified SS : S> withState(block: (SS) -> Unit) {
        val state = uiState.value as? SS ?: return
        block(state)
    }

    protected inline fun <reified SS : S> updateState(block: (SS) -> S) {
        mutableUiState.update { current ->
            val state = current as? SS ?: return@update current
            block(state)
        }
    }
}
