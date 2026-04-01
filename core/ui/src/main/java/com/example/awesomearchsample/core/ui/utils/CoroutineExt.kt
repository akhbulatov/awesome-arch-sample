package com.example.awesomearchsample.core.ui.utils

import kotlinx.coroutines.Job
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun autoCancelJob(): ReadWriteProperty<Any?, Job?> = AutoCancelJobDelegate()

private class AutoCancelJobDelegate : ReadWriteProperty<Any?, Job?> {
    private var job: Job? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): Job? = job

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Job?) {
        job?.cancel()
        job = value
    }
}
