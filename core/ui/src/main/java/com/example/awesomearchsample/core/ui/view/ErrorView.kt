package com.example.awesomearchsample.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.awesomearchsample.core.ui.databinding.CustomErrorViewBinding
import com.example.awesomearchsample.core.ui.error.UiError

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CustomErrorViewBinding =
        CustomErrorViewBinding.inflate(LayoutInflater.from(context), this, true)

    val actionButton: Button
        get() = binding.errorViewActionButton

    fun setError(error: UiError?) {
        with(binding) {
            errorViewTitleLabel.text = error?.title
            isVisible = error != null
        }
    }
}