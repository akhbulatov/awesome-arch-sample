package com.example.awesomearchsample.feature.repo.repos

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.mvvm.updateSuccess
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEvents
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies
import kotlinx.coroutines.launch

internal class ReposViewModel(
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler,
    private val analyticsEventSender: AnalyticsEventSender
) : BaseViewModel<ReposUiState, ReposUiEffect>(initialUiState = ReposUiState.Loading) {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                mutableUiState.value = ReposUiState.Loading
                val repos = getReposUseCase.invoke()
                mutableUiState.value = ReposUiState.Success(repos = repos)
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.value = ReposUiState.Error(error = uiError)
                    }
                )
            }
        }
    }

    fun onSearchClick() {
        emitEffect(ReposUiEffect.NavigateToSearch)
    }

    fun onErrorActionClick() {
        loadRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        mutableUiState.updateSuccess { success: ReposUiState.Success ->
            val updatedRepos = success.repos.updatedByToggleInFavorites(repoBy = repo)
            success.copy(repos = updatedRepos)
        }
    }

    fun onRepoClick(repo: Repo) {
        emitEffect(
            ReposUiEffect.NavigateToRepoDetails(
                repoId = repo.id
            )
        )
        analyticsEventSender.sendEvent(
            AnalyticsEvents.Repo.RepoClick(repoId = repo.id, repoName = repo.name)
        )
    }

    companion object {
        fun viewModelFactory(dependencies: ReposDependencies) = viewModelFactory {
            initializer {
                ReposViewModel(
                    getReposUseCase = dependencies.getReposUseCase,
                    errorHandler = dependencies.uiErrorHandler,
                    analyticsEventSender = dependencies.analyticsEventSender
                )
            }
        }
    }
}
