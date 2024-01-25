package com.example.awesomearchsample.feature.repo.presentation.repos

import android.view.View
import androidx.fragment.app.viewModels
import com.example.awesomearchsample.R
import com.example.awesomearchsample.core.ui.base.BaseFragment
import com.example.awesomearchsample.databinding.FragmentReposBinding
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
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.uiState.observe(viewLifecycleOwner) { setUiState(it) }
    }

    private fun setUiState(uiState: ReposUiState) {
        repoAdapter.submitList(uiState.repos)
    }

    override fun onDestroyView() {
        binding.reposList.adapter = null
        super.onDestroyView()
    }
}