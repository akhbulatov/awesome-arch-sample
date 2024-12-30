package com.example.awesomearchsample.core.ui.util

import android.content.Context
import androidx.annotation.StringRes

class ResourceManager(
    private val context: Context
) {

    fun getString(@StringRes stringRes: Int): String =
        context.getString(stringRes)
}
