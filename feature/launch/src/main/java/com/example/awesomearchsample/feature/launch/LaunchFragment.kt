package com.example.awesomearchsample.feature.launch

import android.os.Bundle
import com.example.awesomearchsample.core.ui.base.BaseFragment
import com.example.awesomearchsample.feature.launch.navigation.LaunchMediator
import com.example.mylibrarycom.example.awesomearchsample.feature.R
import com.example.mylibrarycom.example.awesomearchsample.feature.databinding.FragmentLaunchBinding
import dagger.hilt.android.AndroidEntryPoint
import me.aartikov.alligator.Navigator
import javax.inject.Inject

@AndroidEntryPoint
class LaunchFragment : BaseFragment<FragmentLaunchBinding>(R.layout.fragment_launch) {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var launchMediator: LaunchMediator

    override fun bindBinding(): FragmentLaunchBinding {
        return FragmentLaunchBinding.bind(requireView())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.reset(launchMediator.getMainFlowScreen())
        }
    }
}