package com.example.awesomearchsample.core.ui.text

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class Plain(val value: String) : UiText()
    data class Res(
        @StringRes val id: Int,
        val args: List<Any> = emptyList()
    ) : UiText()

    fun asString(context: Context): String = when (this) {
        is Plain -> value
        is Res -> context.getString(id, *args.toTypedArray())
    }
}
