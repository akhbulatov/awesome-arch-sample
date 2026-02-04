package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.domain.repo.model.Repo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class ReposScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun reposContent_showsRepoNames() {
        val repos = listOf(
            Repo(
                id = 1L,
                name = "Awesome",
                author = "Ada",
                description = "Sample",
                inFavorites = false
            ),
            Repo(
                id = 2L,
                name = "Compose",
                author = "JetBrains",
                description = "UI toolkit",
                inFavorites = false
            )
        )

        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Success(repos = repos),
                    onSearchClick = {},
                    onErrorActionClick = {},
                    onRepoClick = {}
                )
            }
        }

        composeRule.onNodeWithText("Awesome").assertIsDisplayed()
        composeRule.onNodeWithText("Compose").assertIsDisplayed()
    }
}
