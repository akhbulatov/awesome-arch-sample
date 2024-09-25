package com.example.awesomearchsample.feature.repo.repodetails

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.awesomearchsample.core.ui.designsystem.EmptyErrorComponent
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.navigation.BaseScreen
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.feature.repo.R

data class RepoDetailsScreen(private val repoId: Long) : BaseScreen() {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<RepoDetailsViewModel, RepoDetailsViewModel.Factory> { factory ->
            factory.create(repoId = repoId)
        }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is RepoDetailsUiEffect.NavigateTo -> {
                        navigator.push(effect.screen)
                    }
                }
            }
        }

        RepoDetailsContent(
            state = state,
            onNavigationClick = { navigator.pop() },
            onErrorActionClick = viewModel::onErrorActionClick,
            onAuthorClick = viewModel::onAuthorClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RepoDetailsContent(
    state: RepoDetailsUiState,
    onNavigationClick: () -> Unit,
    onErrorActionClick: () -> Unit,
    onAuthorClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.repoDetails?.name.orEmpty())
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
            if (state.emptyProgress) {
                EmptyProgress()
            }
            state.emptyError?.let { emptyError ->
                EmptyError(
                    error = emptyError,
                    onActionClick = onErrorActionClick
                )
            }
            state.repoDetails?.let { repoDetails ->
                RepoDetails(
                    repoDetails = repoDetails,
                    onAuthorClick = onAuthorClick
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
private fun RepoDetails(repoDetails: RepoDetails, onAuthorClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        // Name
        Text(
            text = repoDetails.name,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        // Description
        Text(
            text = repoDetails.description.orEmpty(),
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )
        // Stars
        Text(
            text = stringResource(R.string.repo_details_stars, repoDetails.starsCount),
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge
        )
        // Forks
        Text(
            text = stringResource(R.string.repo_details_forks, repoDetails.forksCount),
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )
        // Author
        Text(
            text = stringResource(R.string.repo_details_author, repoDetails.author),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAuthorClick() },
            style = MaterialTheme.typography.labelSmall
        )
    }
}

// --- Preview --- //

@Preview(showBackground = true)
@Composable
private fun RepoDetailsContentPreview() {
    RepoDetailsContent(
        state = RepoDetailsUiState(
            repoDetails = RepoDetails(
                id = 1,
                name = "AwesomeArchSample",
                author = "akhbulatov",
                description = "Awesome open-source arch sample written in Kotlin",
                starsCount = 99,
                forksCount = 10
            )
        ),
        onNavigationClick = { },
        onErrorActionClick = { },
        onAuthorClick = {}
    )
}
