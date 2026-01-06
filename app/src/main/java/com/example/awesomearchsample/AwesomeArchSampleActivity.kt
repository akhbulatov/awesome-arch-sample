package com.example.awesomearchsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.navigation.AppNavHost

class AwesomeArchSampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppNavHost()
            }
        }
    }
}
