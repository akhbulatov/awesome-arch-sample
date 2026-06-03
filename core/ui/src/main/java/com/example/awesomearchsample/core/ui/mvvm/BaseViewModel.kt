package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : BaseUiEffect> : ViewModel() {

    private val _uiEffects = Channel<E>(capacity = Channel.BUFFERED)
    val uiEffects: Flow<E> = _uiEffects.receiveAsFlow()

    protected fun emitEffect(effect: E) {
        viewModelScope.launch {
            _uiEffects.send(effect)
        }
    }
}
