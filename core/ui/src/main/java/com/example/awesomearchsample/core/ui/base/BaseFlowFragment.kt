package com.example.awesomearchsample.core.ui.base

import android.os.Bundle
import com.example.awesomearchsample.core.ui.R
import com.example.awesomearchsample.core.ui.databinding.FragmentFlowBinding
import com.example.awesomearchsample.core.ui.navigation.ContainerIdProvider
import dagger.hilt.android.AndroidEntryPoint
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.Screen
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFlowFragment : BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow),
    ContainerIdProvider {

    @Inject lateinit var navigator: Navigator

    override val containerId: Int
        get() = R.id.flow_fragment_container

    abstract val startScreen: Screen

    override fun bindBinding(): FragmentFlowBinding {
        return FragmentFlowBinding.bind(requireView())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.reset(startScreen)
        }
    }
}
