package com.example.awesomearchsample.core.ui.util

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getApplicationInstance(): Application {
    val context = LocalContext.current
    return context.applicationContext as Application
}