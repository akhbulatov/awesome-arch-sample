package com.example.awesomearchsample.core.ui.mvvm

import kotlinx.coroutines.flow.MutableStateFlow

interface SuccessState<out T> {
    val data: T
}

inline fun <reified T> Any?.onSuccess(block: (T) -> Unit) {
    val data = (this as? SuccessState<*>)?.data
    if (data is T) {
        block(data)
    }
}

inline fun <reified S : SuccessState<*>, T> MutableStateFlow<T>.updateSuccess(
    block: (S) -> T
) {
    val current = value
    if (current is S) {
        value = block(current)
    }
}
