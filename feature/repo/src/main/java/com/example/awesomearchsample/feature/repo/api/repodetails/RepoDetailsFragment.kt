package com.example.awesomearchsample.feature.repo.api.repodetails

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.example.awesomearchsample.core.ui.base.BaseFragment
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.feature.repo.R
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import com.example.awesomearchsample.feature.repo.databinding.FragmentRepoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.aartikov.alligator.ScreenResolver
import javax.inject.Inject

@AndroidEntryPoint
class RepoDetailsFragment : BaseFragment<FragmentRepoDetailsBinding>(R.layout.fragment_repo_details) {

    @Inject lateinit var screenResolver: ScreenResolver

    private val screen by lazy {
        screenResolver.getScreen<RepoScreens.RepoDetails>(this)
    }
    private val viewModel by viewModels<RepoDetailsViewModel>(
        extrasProducer = {
            MutableCreationExtras(initialExtras = defaultViewModelCreationExtras).apply {
                set(
                    key = DEFAULT_ARGS_KEY,
                    t = bundleOf(RepoDetailsViewModel.ARG_SCREEN to screen)
                )
            }
        }
    )

    override fun bindBinding(): FragmentRepoDetailsBinding {
        return FragmentRepoDetailsBinding.bind(requireView())
    }

    override fun setupView(view: View) {
        super.setupView(view)
        with(binding) {
            repoDetailsToolbar.apply {
                setNavigationOnClickListener {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
            repoDetailsErrorView.actionButton.setOnClickListener {
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

    private fun setUiState(uiState: RepoDetailsUiState) {
        setEmptyProgress(show = uiState.emptyProgress)
        setEmptyError(uiError = uiState.emptyError)
        setRepoDetails(repoDetails = uiState.repoDetails)
    }

    private fun setEmptyProgress(show: Boolean) {
        binding.repoDetailsEmptyProgressBar.isVisible = show
    }

    private fun setEmptyError(uiError: UiError?) {
        binding.repoDetailsErrorView.setError(uiError)
    }

    private fun setRepoDetails(repoDetails: RepoDetails?) {
        with(binding) {
            if (repoDetails != null) {
                repoDetailsToolbar.title = repoDetails.name
                repoDetailsNameLabel.text = repoDetails.name
                repoDetailsDescriptionLabel.text = repoDetails.description
                repoDetailsStarsLabel.text = getString(R.string.repo_details_stars, repoDetails.starsCount)
                repoDetailsForksLabel.text = getString(R.string.repo_details_forks, repoDetails.forksCount)
                repoDetailsAuthorLabel.text = getString(R.string.repo_details_author, repoDetails.author)
            }
            repoDetailsContentContainer.isVisible = repoDetails != null
        }
    }
}