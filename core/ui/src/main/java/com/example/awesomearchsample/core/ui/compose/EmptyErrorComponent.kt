package com.example.awesomearchsample.core.ui.compose

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.awesomearchsample.core.ui.R
import com.example.awesomearchsample.core.ui.error.UiError

@Composable
fun EmptyErrorComponent(
    uiError: UiError,
    onActionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = uiError.title)
        Spacer(
            modifier = Modifier.height(height = 24.dp)
        )
        Button(
            onClick = onActionClick
        ) {
            Text(text = stringResource(R.string.action_refresh))
        }
    }
}
