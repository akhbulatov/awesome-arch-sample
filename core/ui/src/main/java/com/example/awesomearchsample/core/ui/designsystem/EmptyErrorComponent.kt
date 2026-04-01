package com.example.awesomearchsample.core.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.awesomearchsample.core.ui.R
import com.example.awesomearchsample.core.ui.error.UiError

const val ERROR_RETRY_BUTTON_TAG = "error_retry_button"
const val EMPTY_DATA_ACTION_BUTTON_TAG = "empty_data_action_button"

@Composable
fun ErrorComponent(
    error: UiError,
    onActionClick: () -> Unit
) {
    val context = LocalContext.current
    PlaceholderContent(
        title = error.title.asString(context),
        actionText = stringResource(R.string.action_refresh),
        actionTag = ERROR_RETRY_BUTTON_TAG,
        onActionClick = onActionClick
    )
}

@Composable
fun EmptyDataComponent(
    emptyData: UiEmptyData,
    onActionClick: () -> Unit
) {
    val context = LocalContext.current
    PlaceholderContent(
        title = emptyData.title.asString(context),
        actionText = emptyData.actionText?.asString(context),
        actionTag = EMPTY_DATA_ACTION_BUTTON_TAG,
        onActionClick = onActionClick
    )
}

@Composable
private fun PlaceholderContent(
    title: String,
    actionText: String?,
    actionTag: String,
    onActionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        if (actionText != null) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onActionClick,
                modifier = Modifier.testTag(actionTag)
            ) {
                Text(text = actionText)
            }
        }
    }
}
