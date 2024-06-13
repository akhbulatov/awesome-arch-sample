package com.example.awesomearchsample.feature.user.userdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.awesomearchsample.core.ui.designsystem.EmptyErrorComponent
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.navigation.BaseScreen
import com.example.awesomearchsample.domain.user.model.UserDetails

data class UserDetailsScreen(private val login: String) : BaseScreen() {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<UserDetailsViewModel, UserDetailsViewModel.Factory> { factory ->
            factory.create(login = login)
        }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        UserDetailsContent(
            state = state,
            onNavigationClick = { navigator.pop() },
            onErrorActionClick = viewModel::onErrorActionClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailsContent(
    state: UserDetailsUiState,
    onNavigationClick: () -> Unit,
    onErrorActionClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.userDetails?.login.orEmpty())
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
            state.userDetails?.let { userDetails ->
                UserDetails(userDetails)
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
private fun UserDetails(userDetails: UserDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = userDetails.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(
                modifier = Modifier.width(15.dp)
            )
            Column(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            ) {
                // Login
                Text(
                    text = userDetails.login,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge
                )
                // Name
                Text(
                    text = userDetails.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )
        // Bio
        Text(
            text = userDetails.bio.orEmpty(),
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )
        // Location
        Text(
            text = userDetails.location.orEmpty(),
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// --- Preview --- //

@Preview(showBackground = true)
@Composable
private fun UserDetailsContentPreview() {
    UserDetailsContent(
        state = UserDetailsUiState(
            userDetails = UserDetails(
                id = 1,
                login = "akhbulatov",
                name = "Alidibir Akhbulatov",
                avatarUrl = null,
                location = "Makhachkala",
                bio = "Android developer"
            )
        ),
        onNavigationClick = {},
        onErrorActionClick = {}
    )
}