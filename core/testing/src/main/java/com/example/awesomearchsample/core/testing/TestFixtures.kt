package com.example.awesomearchsample.core.testing

import com.example.awesomearchsample.core.commonapi.error.ErrorEntity
import com.example.awesomearchsample.core.commonapi.error.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class FakeErrorHandler(
    private val errorEntity: ErrorEntity = ErrorEntity.Message("error")
) : ErrorHandler {
    override suspend fun getError(throwable: Throwable): ErrorEntity = errorEntity

    override fun recordError(throwable: Throwable) = Unit
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
