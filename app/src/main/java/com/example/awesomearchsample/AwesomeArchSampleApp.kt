package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.di.AppDependencies
import com.example.awesomearchsample.di.AppGraph
import com.example.awesomearchsample.di.AppGraphProvider

class AwesomeArchSampleApp : Application(), AppGraphProvider {

    private val appGraph by lazy { AppGraph(this) }

    override val appDependencies: AppDependencies
        get() = appGraph

    override fun onCreate() {
        super.onCreate()
        appGraph.appInitializers.init()
    }
}
