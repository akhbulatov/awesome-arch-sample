package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.di.AppGraph

class AwesomeArchSampleApp : Application() {

    internal val graph: AppGraph by lazy { AppGraph(this) }

    override fun onCreate() {
        super.onCreate()
        graph.appInitializers.init()
    }
}
