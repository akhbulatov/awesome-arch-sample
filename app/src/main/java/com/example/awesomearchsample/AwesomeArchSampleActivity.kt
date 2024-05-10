package com.example.awesomearchsample

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.awesomearchsample.core.ui.navigation.ContainerIdProvider
import com.example.awesomearchsample.feature.launch.navigation.LaunchScreens
import dagger.hilt.android.AndroidEntryPoint
import me.aartikov.alligator.DestinationType
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.navigationfactories.NavigationFactory
import javax.inject.Inject

@AndroidEntryPoint
class AwesomeArchSampleActivity : AppCompatActivity(R.layout.activity_container) {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var navigationFactory: NavigationFactory
    @Inject lateinit var navigationContextBinder: NavigationContextBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigator.goBack()
            }
        })
        if (savedInstanceState == null) {
            navigator.reset(LaunchScreens.LaunchFlow)
        }
    }

    override fun onResume() {
        super.onResume()
        bindNavigationContext()
    }

    private fun bindNavigationContext() {
        val navigationContext = NavigationContext.Builder(this, navigationFactory)
            .apply {
                val currentFlowFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                if (currentFlowFragment is ContainerIdProvider) {
                    this.fragmentNavigation(
                        currentFlowFragment.childFragmentManager,
                        (currentFlowFragment as ContainerIdProvider).containerId
                    )
                }
            }
            .flowFragmentNavigation(supportFragmentManager, R.id.fragment_container)
            .transitionListener { _, destinationType, _, _ ->
                if (destinationType == DestinationType.FLOW_FRAGMENT) {
                    // Rebind NavigationContext because a current flow fragment has been changed.
                    bindNavigationContext()
                }
            }
            .build()
        navigationContextBinder.bind(navigationContext)
    }

    override fun onPause() {
        navigationContextBinder.unbind(this)
        super.onPause()
    }
}