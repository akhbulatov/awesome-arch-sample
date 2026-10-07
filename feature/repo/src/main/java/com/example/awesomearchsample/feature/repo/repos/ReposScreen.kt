package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.EmptyDataComponent
import com.example.awesomearchsample.core.ui.designsystem.ErrorComponent
import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.mvvm.CollectUiEffects
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.feature.repo.R
import com.example.awesomearchsample.feature.repo.repos.component.RepoList
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies

const val REPOS_SCREEN_TAG = "repos_screen"
const val REPOS_LOADING_TAG = "repos_loading"
const val REPOS_SEARCH_BUTTON_TAG = "repos_search_button"

@Composable
internal fun ReposScreen(
    dependencies: ReposScreenDependencies,
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToRepoDetails: (Long) -> Unit
) {
    val viewModel = viewModel<ReposViewModel>(
        factory = ReposViewModel.viewModelFactory(dependencies = dependencies)
    )
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    CollectUiEffects(viewModel.uiEffects) { effect ->
        when (effect) {
            ReposUiEffect.NavigateToSearch -> onNavigateToSearch()
            ReposUiEffect.NavigateToSettings -> onNavigateToSettings()
            is ReposUiEffect.NavigateToRepoDetails -> onNavigateToRepoDetails(effect.repoId)
            is ReposUiEffect.ShowErrorMessage -> {
                snackbarHostState.showSnackbar(effect.message.asString(context))
            }
        }
    }

    ReposScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onSearchClick = viewModel::onSearchClick,
        onSettingsClick = viewModel::onSettingsClick,
        onErrorActionClick = viewModel::onErrorActionClick,
        onRefresh = viewModel::onRefresh,
        onRepoClick = viewModel::onRepoClick,
        onEmptyDataActionClick = viewModel::onEmptyDataActionClick
    )
}

private typealias OnRepoItemClick = (Repo) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Internal нужна для Compose UI тестов.
internal fun ReposScreen(
    state: ReposUiState,
    snackbarHostState: SnackbarHostState,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onErrorActionClick: () -> Unit,
    onRefresh: () -> Unit,
    onRepoClick: OnRepoItemClick,
    onEmptyDataActionClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.repos_title))
                },
                actions = {
                    IconButton(
                        onClick = onSearchClick,
                        modifier = Modifier.testTag(REPOS_SEARCH_BUTTON_TAG)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_search),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = onSettingsClick
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_settings),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag(REPOS_SCREEN_TAG)
        ) {
            when (state) {
                ReposUiState.Loading -> {
                    ReposLoading()
                }
                is ReposUiState.Error -> {
                    ReposError(
                        error = state.error,
                        onActionClick = onErrorActionClick
                    )
                }
                is ReposUiState.Success -> {
                    ReposSuccess(
                        repos = state.repos,
                        isRefreshing = state.isRefreshing,
                        onRefresh = onRefresh,
                        onRepoClick = onRepoClick,
                        onEmptyDataActionClick = onEmptyDataActionClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ReposLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .testTag(REPOS_LOADING_TAG)
    )
}

@Composable
private fun ReposError(
    error: UiError,
    onActionClick: () -> Unit
) {
    ErrorComponent(
        error = error,
        onActionClick = onActionClick
    )
}

@Composable
private fun ReposSuccess(
    repos: List<Repo>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onRepoClick: OnRepoItemClick,
    onEmptyDataActionClick: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize()
    ) {
        if (repos.isNotEmpty()) {
            RepoList(
                repos = repos,
                onRepoItemClick = onRepoClick
            )
        } else {
            ReposEmptyData(
                onActionClick = onEmptyDataActionClick
            )
        }
    }
}

@Composable
private fun ReposEmptyData(
    onActionClick: () -> Unit
) {
    EmptyDataComponent(
        emptyData = UiEmptyData(
            title = UiText.Res(R.string.repos_empty_title),
            actionText = UiText.Res(com.example.awesomearchsample.core.ui.R.string.action_refresh)
        ),
        onActionClick = onActionClick
    )
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun ReposScreenSuccessPreview() {
    AppTheme {
        ReposScreen(
            state = ReposUiState.Success(
                repos = listOf(
                    Repo(
                        id = 0L,
                        name = "AwesomeArchSample: 0",
                        author = "akhbulatov",
                        description = "Awesome open-source arch sample written in Kotlin"
                    )
                )
            ),
            snackbarHostState = SnackbarHostState(),
            onSearchClick = {},
            onSettingsClick = {},
            onErrorActionClick = {},
            onRefresh = {},
            onRepoClick = {},
            onEmptyDataActionClick = {}
        )
    }
}
//endregion
