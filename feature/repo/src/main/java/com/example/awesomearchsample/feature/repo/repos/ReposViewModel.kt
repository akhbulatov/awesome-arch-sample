package com.example.awesomearchsample.feature.repo.repos

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.commonapi.util.autoCancelJob
import com.example.awesomearchsample.core.commonapi.util.launchCatching
import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEvents
import com.example.awesomearchsample.feature.repo.R
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies
import kotlinx.coroutines.flow.update

internal class ReposViewModel(
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler,
    private val analyticsEventSender: AnalyticsEventSender
) : BaseViewModel<ReposUiState, ReposUiEffect>(initialUiState = ReposUiState()) {

    private var reposJob by autoCancelJob()

    init {
        loadRepos()
    }

    private fun loadRepos() {
        reposJob = viewModelScope.launchCatching(
            onFailure = { e ->
                errorHandler.proceed(e) { uiError ->
                    mutableUiState.value = ReposUiState(initialError = uiError)
                }
            }
        ) {
            mutableUiState.value = ReposUiState(isInitialLoading = true)

            val repos = getReposUseCase.invoke()
            mutableUiState.value = if (repos.isNotEmpty()) {
                ReposUiState(
                    content = ReposContent(repos = repos)
                )
            } else {
                ReposUiState(
                    initialEmptyData = UiEmptyData(
                        title = UiText.Res(R.string.repos_empty_title),
                        actionText = UiText.Res(com.example.awesomearchsample.core.ui.R.string.action_refresh)
                    )
                )
            }
        }
    }

    private fun refreshRepos() {
        reposJob = viewModelScope.launchCatching(
            onFailure = { e ->
                errorHandler.proceed(e) { uiError ->
                    emitEffect(ReposUiEffect.ShowErrorMessage(message = uiError.title))
                }
            },
            onFinally = {
                mutableUiState.update { it.copy(isRefreshing = false) }
            }
        ) {
            mutableUiState.update { it.copy(isRefreshing = true) }

            val repos = getReposUseCase.invoke()
            mutableUiState.value = if (repos.isNotEmpty()) {
                ReposUiState(
                    content = ReposContent(repos = repos),
                )
            } else {
                ReposUiState(
                    initialEmptyData = UiEmptyData(
                        title = UiText.Res(R.string.repos_empty_title),
                        actionText = UiText.Res(com.example.awesomearchsample.core.ui.R.string.action_refresh)
                    )
                )
            }
        }
    }

    fun onSearchClick() {
        emitEffect(ReposUiEffect.NavigateToSearch)
    }

    fun onSettingsClick() {
        emitEffect(ReposUiEffect.NavigateToSettings)
    }

    fun onErrorActionClick() {
        loadRepos()
    }

    fun onRefresh() {
        refreshRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        mutableUiState.update { state ->
            val content = state.content ?: return@update state

            state.copy(
                content = content.copy(
                    repos = content.repos.updatedByToggleInFavorites(repoBy = repo)
                )
            )
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
        fun viewModelFactory(dependencies: ReposScreenDependencies) = viewModelFactory {
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
