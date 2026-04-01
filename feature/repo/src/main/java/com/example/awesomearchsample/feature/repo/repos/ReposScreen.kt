package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.EmptyDataComponent
import com.example.awesomearchsample.core.ui.designsystem.ErrorComponent
import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.feature.repo.R
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies

const val REPOS_SCREEN_TAG = "repos_screen"
const val REPOS_LOADING_TAG = "repos_loading"
const val REPOS_SEARCH_BUTTON_TAG = "repos_search_button"
const val REPOS_LIST_TAG = "repos_list"
const val REPO_ITEM_TAG_PREFIX = "repo_item_"

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
        viewModel.uiEffect.collect { effect ->
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

    ReposContent(
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
internal fun ReposContent(
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
                    else -> {
                        ReposData(
                            data = state.data,
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
private fun ReposData(
    data: ReposUiData,
    onRepoClick: OnRepoItemClick
) {
    RepoList(
        repos = data.repos,
        onRepoItemClick = onRepoClick
    )
}

@Composable
private fun RepoList(
    repos: List<Repo>,
    onRepoItemClick: OnRepoItemClick
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(REPOS_LIST_TAG)
    ) {
        items(
            items = repos,
            key = { repo -> repo.id }
        ) { repo ->
            RepoItem(repo, onRepoItemClick)
        }
    }
}

@Composable
private fun RepoItem(
    repo: Repo,
    onRepoItemClick: OnRepoItemClick
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRepoItemClick(repo) }
            .padding(15.dp)
            .testTag(REPO_ITEM_TAG_PREFIX + repo.id)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Name
            Text(
                text = repo.name,
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(15.dp))

            // Author
            Text(
                text = repo.author,
                color = Color(0xDE000000),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = repo.description.orEmpty(),
            modifier = Modifier.fillMaxWidth(),
            color = Color(0x99000000)
        )
    }
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun ReposContentSuccessPreview() {
    AppTheme {
        ReposContent(
            state = ReposUiState(
                data = ReposUiData(repos = ReposPreviewData.list),
                isInitialLoading = false
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

@Preview(showBackground = true)
@Composable
private fun RepoItemPreview() {
    AppTheme {
        RepoItem(
            repo = ReposPreviewData.item,
            onRepoItemClick = {}
        )
    }
}

private object ReposPreviewData {
    val item = Repo(
        id = 0L,
        name = "AwesomeArchSample: 0",
        author = "akhbulatov",
        description = "Awesome open-source arch sample written in Kotlin"
    )

    val list = List(5) { index ->
        item.copy(
            id = index.toLong(),
            name = "AwesomeArchSample: $index"
        )
    }
}
//endregion
