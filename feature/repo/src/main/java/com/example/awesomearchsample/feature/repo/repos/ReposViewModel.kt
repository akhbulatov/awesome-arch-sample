package com.example.awesomearchsample.feature.repo.repos

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.core.ui.utils.autoCancelJob
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEvents
import com.example.awesomearchsample.feature.repo.R
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

internal class ReposViewModel(
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler,
    private val analyticsEventSender: AnalyticsEventSender
) : BaseViewModel<ReposUiState, ReposUiEffect>(initialUiState = ReposUiState()) {

    private var loadReposJob: Job? by autoCancelJob()

    init {
        loadInitialRepos()
    }

    private fun loadInitialRepos() {
        loadReposJob = viewModelScope.launch {
            try {
                mutableUiState.value = ReposUiState(isInitialLoading = true)

                val repos = getReposUseCase.invoke()
                mutableUiState.value = if (repos.isNotEmpty()) {
                    ReposUiState(
                        data = ReposUiData(repos = repos)
                    )
                } else {
                    ReposUiState(
                        data = ReposUiData(),
                        isInitialLoading = false,
                        initialEmptyData = UiEmptyData(
                            title = UiText.Res(R.string.repos_empty_title),
                            actionText = UiText.Res(com.example.awesomearchsample.core.ui.R.string.action_refresh)
                        )
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                errorHandler.proceed(e) { uiError ->
                    mutableUiState.value = ReposUiState(
                        initialError = uiError
                    )
                }
            } finally {
                mutableUiState.update { it.copy(isInitialLoading = false) }
            }
        }
    }

    private fun refreshRepos() {
        loadReposJob = viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(isRefreshing = true) }

                val repos = getReposUseCase.invoke()
                mutableUiState.value = if (repos.isNotEmpty()) {
                    ReposUiState(
                        data = ReposUiData(repos = repos),
                        isInitialLoading = false
                    )
                } else {
                    ReposUiState(
                        data = ReposUiData(),
                        isInitialLoading = false,
                        initialEmptyData = UiEmptyData(
                            title = UiText.Res(R.string.repos_empty_title),
                            actionText = UiText.Res(com.example.awesomearchsample.core.ui.R.string.action_refresh)
                        )
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                errorHandler.proceed(e) { uiError ->
                    emitEffect(ReposUiEffect.ShowErrorMessage(message = uiError.title))
                }
            } finally {
                mutableUiState.update { it.copy(isRefreshing = false) }
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
        loadInitialRepos()
    }

    fun onRefresh() {
        refreshRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        mutableUiState.update { state ->
            state.copy(
                data = state.data.copy(
                    repos = state.data.repos.updatedByToggleInFavorites(repoBy = repo)
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
