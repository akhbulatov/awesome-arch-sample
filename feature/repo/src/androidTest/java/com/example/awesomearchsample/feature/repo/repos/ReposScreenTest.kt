package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.ERROR_RETRY_BUTTON_TAG
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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

    @Test
    fun reposContent_clickOnItem_invokesCallback() {
        val repo = Repo(
            id = 10L,
            name = "ClickMe",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )
        var clickedRepo: Repo? = null

        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Success(repos = listOf(repo)),
                    onSearchClick = {},
                    onErrorActionClick = {},
                    onRepoClick = { clickedRepo = it }
                )
            }
        }

        composeRule.onNodeWithText("ClickMe").performClick()

        composeRule.runOnIdle {
            assertEquals(repo, clickedRepo)
        }
    }

    @Test
    fun reposContent_errorState_showsRefreshAndInvokesCallback() {
        var retryClicked = false

        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Error(
                        error = UiError(UiText.Plain("Error"))
                    ),
                    onSearchClick = {},
                    onErrorActionClick = { retryClicked = true },
                    onRepoClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, retryClicked)
        }
    }

    @Test
    fun reposContent_loadingState_showsProgress() {
        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Loading,
                    onSearchClick = {},
                    onErrorActionClick = {},
                    onRepoClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(REPOS_LOADING_TAG).assertIsDisplayed()
    }

    @Test
    fun reposContent_idleState_showsNoLoadingOrError() {
        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Initial,
                    onSearchClick = {},
                    onErrorActionClick = {},
                    onRepoClick = {}
                )
            }
        }

        composeRule.onAllNodesWithTag(REPOS_LOADING_TAG).assertCountEquals(0)
        composeRule.onAllNodesWithTag(ERROR_RETRY_BUTTON_TAG).assertCountEquals(0)
    }

    @Test
    fun reposContent_searchClick_invokesCallback() {
        var searchClicked = false

        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Success(repos = emptyList()),
                    onSearchClick = { searchClicked = true },
                    onErrorActionClick = {},
                    onRepoClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(REPOS_SEARCH_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, searchClicked)
        }
    }

    @Test
    fun reposContent_successWithEmptyList_doesNotShowList() {
        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = ReposUiState.Success(repos = emptyList()),
                    onSearchClick = {},
                    onErrorActionClick = {},
                    onRepoClick = {}
                )
            }
        }

        composeRule.onAllNodesWithTag(REPOS_LIST_TAG).assertCountEquals(0)
    }
}
