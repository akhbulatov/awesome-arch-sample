package com.example.awesomearchsample.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.awesomearchsample.core.ui.designsystem.EmptyErrorComponent
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.navigation.BaseScreen
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.feature.search.di.rememberSearchDependencies

private typealias OnRepoResultItemClick = (Repo) -> Unit

object SearchScreen : BaseScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val dependencies = rememberSearchDependencies()
        val viewModel = viewModel<SearchViewModel>(
            factory = SearchViewModel.factory(dependencies = dependencies)
        )
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is SearchUiEffect.NavigateTo -> {
                        navigator.push(effect.screen)
                    }
                }
            }
        }

        SearchContent(
            state = state,
            queryInput = viewModel.queryInput,
            onQueryInputChange = viewModel::onSearchQueryInputChanged,
            onNavigationClick = { navigator.pop() },
            onErrorActionClick = viewModel::onErrorActionClick,
            onSearchActionClick = viewModel::onSearchActionClick,
            onRepoResultItemClick = viewModel::onRepoResultItemClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchContent(
    state: SearchUiState,
    queryInput: String,
    onQueryInputChange: (String) -> Unit,
    onNavigationClick: () -> Unit,
    onErrorActionClick: () -> Unit,
    onSearchActionClick: () -> Unit,
    onRepoResultItemClick: OnRepoResultItemClick
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    OutlinedTextField(
                        value = queryInput,
                        onValueChange = { onQueryInputChange.invoke(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.search_repo_hint)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                keyboardController?.hide()
                                onSearchActionClick.invoke()
                            }
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigationClick
                    ) {
                        Image(
                            painter = painterResource(com.example.awesomearchsample.core.ui.R.drawable.ic_arrow_back),
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
            when (state) {
                is SearchUiState.Loading -> EmptyProgress()
                is SearchUiState.Error -> EmptyError(
                    error = state.error,
                    onActionClick = onErrorActionClick
                )
                is SearchUiState.Success -> SearchSuccess(
                    state = state,
                    onRepoResultItemClick = onRepoResultItemClick
                )
                is SearchUiState.Idle -> {
                    if (state.recentQueries.isNotEmpty()) {
                        RecentQueryList(queries = state.recentQueries)
                    }
                }
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
private fun SearchSuccess(
    state: SearchUiState.Success,
    onRepoResultItemClick: OnRepoResultItemClick
) {
    when (val result = state.result) {
        is SearchResult.Repos -> ReposResult(
            result = result,
            onResultItemClick = onRepoResultItemClick
        )
    }
}

@Composable
private fun ReposResult(result: SearchResult.Repos, onResultItemClick: OnRepoResultItemClick) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = result.data,
            key = { repo -> repo.id }
        ) { repo ->
            RepoResultItem(
                item = repo,
                onItemClick = onResultItemClick
            )
        }
    }
}

@Composable
private fun RepoResultItem(item: Repo, onItemClick: OnRepoResultItemClick) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke(item) }
            .padding(15.dp)
    ) {
        // Name
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        // Author
        Text(
            text = item.author,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun RecentQueryList(queries: List<SearchQuery>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = queries,
            key = { query -> query.value }
        ) { query ->
            RecentQueryItem(query = query)
        }
    }
}

@Composable
private fun RecentQueryItem(query: SearchQuery) {
    Text(
        text = query.value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    )
}

// --- Preview --- //
@Preview(showBackground = true)
@Composable
private fun SearchContentPreview() {
    SearchContent(
        state = SearchUiState.Success(
            result = SearchResult.Repos(
                data = buildList {
                    repeat(5) { index ->
                        add(createRepoForPreview(index))
                    }
                }
            )
        ),
        queryInput = "awesome arch sample",
        onQueryInputChange = {},
        onNavigationClick = {},
        onErrorActionClick = {},
        onSearchActionClick = {},
        onRepoResultItemClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RepoResultItem() {
    createRepoForPreview(index = 0)
}

private fun createRepoForPreview(index: Int) = Repo(
    id = index.toLong(),
    name = "AwesomeArchSample: $index",
    author = "akhbulatov",
    description = "Awesome open-source arch sample written in Kotlin"
)

@Preview(showBackground = true)
@Composable
private fun RecentQueryItemPreview() {
    RecentQueryItem(
        query = SearchQuery("awesome arch sample")
    )
}
