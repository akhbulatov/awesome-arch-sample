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
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(Unit) {
        viewModel.uiEffects.collect { effect ->
            when (effect) {
                ReposUiEffect.NavigateToSearch -> onNavigateToSearch()
                ReposUiEffect.NavigateToSettings -> onNavigateToSettings()
                is ReposUiEffect.NavigateToRepoDetails -> onNavigateToRepoDetails(effect.repoId)
                is ReposUiEffect.ShowErrorMessage -> {
                    snackbarHostState.showSnackbar(effect.message.asString(context))
                }
            }
        }
    }

    ReposScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onSearchClick = viewModel::onSearchClick,
        onSettingsClick = viewModel::onSettingsClick,
        onErrorActionClick = viewModel::onErrorActionClick,
        onEmptyDataActionClick = viewModel::onRefresh,
        onRefresh = viewModel::onRefresh,
        onRepoClick = viewModel::onRepoClick
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
    onEmptyDataActionClick: () -> Unit,
    onRefresh: () -> Unit,
    onRepoClick: OnRepoItemClick
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
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag(REPOS_SCREEN_TAG)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    state.isInitialLoading -> {
                        ReposInitialLoading()
                    }
                    state.initialError != null -> {
                        ReposInitialError(
                            error = state.initialError,
                            onActionClick = onErrorActionClick
                        )
                    }
                    state.initialEmptyData != null -> {
                        ReposInitialEmptyData(
                            emptyData = state.initialEmptyData,
                            onActionClick = onEmptyDataActionClick
                        )
                    }
                    state.content != null -> {
                        ReposContent(
                            content = state.content,
                            onRepoClick = onRepoClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ReposInitialLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .testTag(REPOS_LOADING_TAG)
    )
}

@Composable
private fun ReposInitialError(
    error: UiError,
    onActionClick: () -> Unit
) {
    ErrorComponent(
        error = error,
        onActionClick = onActionClick
    )
}

@Composable
private fun ReposInitialEmptyData(
    emptyData: UiEmptyData,
    onActionClick: () -> Unit
) {
    EmptyDataComponent(
        emptyData = emptyData,
        onActionClick = onActionClick
    )
}

@Composable
private fun ReposContent(
    content: ReposContent,
    onRepoClick: OnRepoItemClick
) {
    RepoList(
        repos = content.repos,
        onRepoItemClick = onRepoClick
    )
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun ReposScreenContentPreview() {
    AppTheme {
        ReposScreen(
            state = ReposUiState(
                content = ReposContent(
                    repos = listOf(
                        Repo(
                            id = 0L,
                            name = "AwesomeArchSample: 0",
                            author = "akhbulatov",
                            description = "Awesome open-source arch sample written in Kotlin"
                        )
                    )
                )
            ),
            snackbarHostState = SnackbarHostState(),
            onSearchClick = {},
            onSettingsClick = {},
            onErrorActionClick = {},
            onEmptyDataActionClick = {},
            onRefresh = {},
            onRepoClick = {}
        )
    }
}
//endregion
