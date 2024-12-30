package com.example.awesomearchsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.feature.launch.host.LaunchHostScreen

class AwesomeArchSampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Navigator(
                    screen = LaunchHostScreen,
                    key = "AppNavigator"
                )
            }
        }
    }
}