package com.example.awesomearchsample.core.ui.mvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

/** Collects a stable [effects] flow for the lifetime of this composition. */
@Composable
fun <E : BaseUiEffect> CollectUiEffects(
    effects: Flow<E>,
    onEffect: suspend (E) -> Unit
) {
    LaunchedEffect(Unit) {
        effects.collect { effect ->
            onEffect(effect)
        }
    }
}
