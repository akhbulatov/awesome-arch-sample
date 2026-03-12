package com.example.awesomearchsample.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.awesomearchsample.feature.settings.di.SettingsScreenDependencies

@Composable
internal fun SettingsScreen(
    dependencies: SettingsScreenDependencies,
    onBack: () -> Unit,
    onNavigateToAdvancedSettings: () -> Unit
) {
    val viewModel = viewModel<SettingsViewModel>(
        factory = SettingsViewModel.viewModelFactory(dependencies = dependencies)
    )
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsContent(
        state = state,
        onBack = onBack,
        onNotificationsToggle = viewModel::onNotificationsToggle,
        onAdvancedClick = onNavigateToAdvancedSettings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent(
    state: SettingsUiState,
    onBack: () -> Unit,
    onNotificationsToggle: () -> Unit,
    onAdvancedClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings_title))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(com.example.awesomearchsample.core.ui.R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .toggleable(
                        value = state.notificationsEnabled,
                        onValueChange = { onNotificationsToggle() },
                        role = Role.Switch
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.settings_enable_notifications)
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = state.notificationsEnabled,
                    onCheckedChange = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable(onClick = onAdvancedClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.settings_advanced_title)
                )
            }
        }
    }
}
