package com.example.awesomearchsample.feature.user.userdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.ErrorComponent
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.feature.user.navigation.userdetails.UserDetailsRoute
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsScreenDependencies

const val USER_DETAILS_SCREEN_TAG = "user_details_screen"

@Composable
internal fun UserDetailsScreen(
    route: UserDetailsRoute,
    dependencies: UserDetailsScreenDependencies,
    onBack: () -> Unit
) {
    val args = UserDetailsViewModel.Args(
        login = route.login
    )
    val viewModel = viewModel<UserDetailsViewModel>(
        factory = UserDetailsViewModel.viewModelFactory(
            args = args,
            dependencies = dependencies
        )
    )
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    UserDetailsScreen(
        state = state,
        onNavigationClick = onBack,
        onErrorActionClick = viewModel::onErrorActionClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserDetailsScreen(
    state: UserDetailsUiState,
    onNavigationClick: () -> Unit,
    onErrorActionClick: () -> Unit
) {
    val title = state.content?.userDetails?.login.orEmpty()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
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
                .testTag(USER_DETAILS_SCREEN_TAG)
        ) {
            when {
                state.isInitialLoading -> UserDetailsInitialLoading()
                state.initialError != null -> UserDetailsInitialError(
                    error = state.initialError,
                    onActionClick = onErrorActionClick
                )
                state.content != null -> UserDetailsContentBody(content = state.content)
            }
        }
    }
}

@Composable
private fun UserDetailsInitialLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
private fun UserDetailsInitialError(
    error: UiError,
    onActionClick: () -> Unit
) {
    ErrorComponent(
        error = error,
        onActionClick = onActionClick
    )
}

@Composable
private fun UserDetailsContentBody(content: UserDetailsContent) {
    val userDetails = content.userDetails
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = userDetails.avatarUrl,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
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
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge
                )
                // Name
                Text(
                    text = userDetails.name,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Bio
        Text(
            text = userDetails.bio.orEmpty(),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Location
        Text(
            text = userDetails.location.orEmpty(),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

//region Previews
@Preview
@Composable
private fun UserDetailsScreenPreview() {
    AppTheme {
        UserDetailsScreen(
            state = UserDetailsUiState(
                content = UserDetailsContent(
                    userDetails = UserDetailsPreviewData.item
                )
            ),
            onNavigationClick = {},
            onErrorActionClick = {}
        )
    }
}

private object UserDetailsPreviewData {
    val item = UserDetails(
        id = 1L,
        login = "akhbulatov",
        name = "Alidibir Akhbulatov",
        avatarUrl = null,
        location = "Makhachkala",
        bio = "Android developer"
    )
}
//endregion
