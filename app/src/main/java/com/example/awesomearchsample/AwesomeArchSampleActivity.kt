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

        val appDependencies = (application as AppGraphProvider).appDependencies

        setContent {
            AppTheme {
                AppNavHost(dependencies = appDependencies)
            }
        }
    }
}
