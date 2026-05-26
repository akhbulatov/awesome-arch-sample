package com.example.awesomearchsample.core.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : BaseUiEffect> : ViewModel() {

    private val mutableUiEffect = Channel<E>(capacity = Channel.BUFFERED)
    val uiEffect = mutableUiEffect.receiveAsFlow()

    protected fun emitEffect(effect: E) {
        viewModelScope.launch {
            mutableUiEffect.send(effect)
        }
    }
}
