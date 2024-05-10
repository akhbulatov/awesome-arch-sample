package com.example.awesomearchsample.feature.user.api.userdetails

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import coil.load
import com.example.awesomearchsample.core.ui.base.BaseFragment
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.feature.user.R
import com.example.awesomearchsample.feature.user.api.navigation.UserScreens
import com.example.awesomearchsample.feature.user.databinding.FragmentUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.aartikov.alligator.ScreenResolver
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>(R.layout.fragment_user_details) {

    @Inject lateinit var screenResolver: ScreenResolver

    private val screen by lazy {
        screenResolver.getScreen<UserScreens.UserDetails>(this)
    }
    private val viewModel by viewModels<UserDetailsViewModel>(
        extrasProducer = {
            MutableCreationExtras(initialExtras = defaultViewModelCreationExtras).apply {
                set(
                    key = DEFAULT_ARGS_KEY,
                    t = bundleOf(UserDetailsViewModel.ARG_SCREEN to screen)
                )
            }
        }
    )

    override fun bindBinding(): FragmentUserDetailsBinding {
        return FragmentUserDetailsBinding.bind(requireView())
    }

    override fun setupView(view: View) {
        super.setupView(view)
        with(binding) {
            userDetailsToolbar.apply {
                setNavigationOnClickListener {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
            userDetailsErrorView.actionButton.setOnClickListener {
                viewModel.onErrorActionClick()
            }
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { setUiState(it) }
            }
        }
    }

    private fun setUiState(uiState: UserDetailsUiState) {
        setEmptyProgress(show = uiState.emptyProgress)
        setEmptyError(uiError = uiState.emptyError)
        setUserDetails(userDetails = uiState.userDetails)
    }

    private fun setEmptyProgress(show: Boolean) {
        binding.userDetailsEmptyProgressBar.isVisible = show
    }

    private fun setEmptyError(uiError: UiError?) {
        binding.userDetailsErrorView.setError(uiError)
    }

    private fun setUserDetails(userDetails: UserDetails?) {
        with(binding) {
            if (userDetails != null) {
                userDetailsToolbar.title = userDetails.login
                userDetailsAvatarImage.load(userDetails.avatarUrl)
                userDetailsLoginLabel.text = userDetails.login
                userDetailsNameLabel.text = userDetails.name
                userDetailsBioLabel.text = userDetails.bio
                userDetailsLocationLabel.text = userDetails.location
            }
            userDetailsContentContainer.isVisible = userDetails != null
        }
    }
}