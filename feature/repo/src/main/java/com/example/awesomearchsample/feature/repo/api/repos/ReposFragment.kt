package com.example.awesomearchsample.feature.repo.api.repos

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.awesomearchsample.core.ui.base.BaseFragment
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.feature.repo.R
import com.example.awesomearchsample.feature.repo.databinding.FragmentReposBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReposFragment : BaseFragment<FragmentReposBinding>(R.layout.fragment_repos) {

    private val viewModel by viewModels<ReposViewModel>()

    private val repoAdapter by lazy {
        RepoAdapter(
            favoritesClickListener = { viewModel.onFavoritesClick(it) }
        )
    }

    override fun bindBinding(): FragmentReposBinding {
        return FragmentReposBinding.bind(requireView())
    }

    override fun setupView(view: View) {
        super.setupView(view)
        with(binding) {
            reposList.apply {
                addItemDecoration(
                    MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL)
                )
                adapter = repoAdapter
            }
            reposErrorView.actionButton.setOnClickListener {
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

    private fun setUiState(uiState: ReposUiState) {
        setEmptyProgress(show = uiState.emptyProgress)
        setEmptyError(uiError = uiState.emptyError)
        setRepos(repos = uiState.repos)
    }

    private fun setEmptyProgress(show: Boolean) {
        binding.reposEmptyProgressBar.isVisible = show
    }

    private fun setEmptyError(uiError: UiError?) {
        binding.reposErrorView.setError(uiError)
    }

    private fun setRepos(repos: List<Repo>) {
        repoAdapter.submitList(repos)
    }

    override fun onDestroyView() {
        binding.reposList.adapter = null
        super.onDestroyView()
    }
}