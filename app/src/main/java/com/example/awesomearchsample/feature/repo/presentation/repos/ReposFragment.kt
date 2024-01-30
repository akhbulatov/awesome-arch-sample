package com.example.awesomearchsample.feature.repo.presentation.repos

import android.view.View
import androidx.fragment.app.viewModels
import com.example.awesomearchsample.R
import com.example.awesomearchsample.core.ui.base.BaseFragment
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.databinding.FragmentReposBinding
import com.example.awesomearchsample.feature.repo.domain.model.Repo
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposFragment : BaseFragment<FragmentReposBinding>(R.layout.fragment_repos) {

    private val viewModel by viewModels<ReposViewModel>()

    private val repoAdapter by lazy {
        RepoAdapter()
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
        viewModel.uiState.observe(viewLifecycleOwner) { setUiState(it) }
    }

    private fun setUiState(uiState: ReposUiState) {
        setRepos(repos = uiState.repos)
        setEmptyError(uiError = uiState.emptyError)
    }

    private fun setRepos(repos: List<Repo>) {
        repoAdapter.submitList(repos)
    }

    private fun setEmptyError(uiError: UiError?) {
        binding.reposErrorView.setError(uiError)
    }

    override fun onDestroyView() {
        binding.reposList.adapter = null
        super.onDestroyView()
    }
}