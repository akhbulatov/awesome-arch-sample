package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.awesomearchsample.core.ui.designsystem.EmptyErrorComponent
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.navigation.BaseScreen
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.feature.repo.R

private typealias OnRepoItemClick = (Repo) -> Unit

object ReposScreen : BaseScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<ReposViewModel>()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect { event ->
                when (event) {
                    is ReposUiEffect.NavigateTo -> {
                        navigator.push(event.screen)
                    }
                }
            }
        }

        ReposContent(
            state = state,
            onSearchClick = viewModel::onSearchClick,
            onErrorActionClick = viewModel::onErrorActionClick,
            onRepoClick = viewModel::onRepoClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReposContent(
    state: ReposUiState,
    onSearchClick: () -> Unit,
    onErrorActionClick: () -> Unit,
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
                        onClick = onSearchClick
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_search),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (state.emptyProgress) {
                EmptyProgress()
            }
            state.emptyError?.let { emptyError ->
                EmptyError(
                    error = emptyError,
                    onActionClick = onErrorActionClick
                )
            }
            if (state.repos.isNotEmpty()) {
                RepoList(
                    repos = state.repos,
                    onRepoItemClick = onRepoClick
                )
            }
        }
    }
}

@Composable
private fun BoxScope.EmptyProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .align(alignment = Alignment.Center)
    )
}

@Composable
private fun EmptyError(error: UiError, onActionClick: () -> Unit) {
    EmptyErrorComponent(
        uiError = error,
        onActionClick = onActionClick
    )
}

@Composable
private fun RepoList(repos: List<Repo>, onRepoItemClick: OnRepoItemClick) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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
private fun RepoItem(repo: Repo, onRepoItemClick: OnRepoItemClick) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRepoItemClick(repo) }
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Name
            Text(
                text = repo.name,
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f),
                fontSize = 16.sp
            )
            Spacer(
                modifier = Modifier.width(15.dp)
            )
            // Author
            Text(
                text = repo.author,
                color = Color(0xDE000000),
                fontSize = 12.sp
            )
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        // Description
        Text(
            text = repo.description.orEmpty(),
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0x99000000)
        )
    }
}

// --- Preview --- //

@Preview(showBackground = true)
@Composable
private fun ReposContentPreview() {
    ReposContent(
        state = ReposUiState(
            repos = buildList {
                repeat(5) { index ->
                    add(createRepoForPreview(index))
                }
            }
        ),
        onSearchClick = {},
        onErrorActionClick = {},
        onRepoClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RepoItemPreview() {
    RepoItem(
        repo = createRepoForPreview(index = 0),
        onRepoItemClick = {}
    )
}

private fun createRepoForPreview(index: Int) = Repo(
    id = index.toLong(),
    name = "AwesomeArchSample: $index",
    author = "akhbulatov",
    description = "Awesome open-source arch sample written in Kotlin"
)