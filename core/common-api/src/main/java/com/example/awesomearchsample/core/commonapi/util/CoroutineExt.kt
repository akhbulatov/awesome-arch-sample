package com.example.awesomearchsample.core.commonapi.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
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

fun CoroutineScope.launchCatching(
    onFailure: suspend (Throwable) -> Unit = {},
    onFinally: suspend () -> Unit = {},
    action: suspend CoroutineScope.() -> Unit,
): Job = launch {
    try {
        action()
    } catch (e: CancellationException) {
        throw e
    } catch (t: Throwable) {
        onFailure(t)
    } finally {
        onFinally()
    }
}

suspend inline fun <R> runCatchingCancellable(
    crossinline block: suspend () -> R,
): Result<R> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (t: Throwable) {
    Result.failure(t)
}
