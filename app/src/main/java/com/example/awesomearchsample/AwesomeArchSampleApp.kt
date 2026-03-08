package com.example.awesomearchsample

import android.app.Application
import com.example.awesomearchsample.di.AppGraph
import com.example.awesomearchsample.di.AppGraphProvider

class AwesomeArchSampleApp : Application(), AppGraphProvider {

    override val appGraph: AppGraph by lazy { AppGraph(this) }

    override fun onCreate() {
        super.onCreate()
        appGraph.appInitializers.init()
    }
}
