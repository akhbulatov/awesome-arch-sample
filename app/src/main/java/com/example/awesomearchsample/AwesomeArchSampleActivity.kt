package com.example.awesomearchsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.di.AppGraphProvider
import com.example.awesomearchsample.navigation.AppNavHost

class AwesomeArchSampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appGraph = (application as AppGraphProvider).appGraph

        setContent {
            AppTheme {
                AppNavHost(
                    launchFeatureDependencies = appGraph.launchFeatureDependencies,
                    repoFeatureDependencies = appGraph.repoFeatureDependencies,
                    searchFeatureDependencies = appGraph.searchFeatureDependencies,
                    settingsFeatureDependencies = appGraph.settingsFeatureDependencies,
                    userFeatureDependencies = appGraph.userFeatureDependencies
                )
            }
        }
    }
}
