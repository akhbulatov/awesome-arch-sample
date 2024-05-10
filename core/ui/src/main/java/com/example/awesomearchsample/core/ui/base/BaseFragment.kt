package com.example.awesomearchsample.core.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = bindBinding()
        setupView(view)
        setupViewModel()
    }

    abstract fun bindBinding(): VB

    open fun setupView(view: View) {}
    open fun setupViewModel() {}

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
