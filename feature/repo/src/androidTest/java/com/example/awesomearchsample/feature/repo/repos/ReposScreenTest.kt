package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.EMPTY_DATA_ACTION_BUTTON_TAG
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
    fun reposScreen_showsRepoNames() {
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

        setReposScreen(
            state = ReposUiState.Success(repos = repos)
        )

        composeRule.onNodeWithText("Awesome").assertIsDisplayed()
        composeRule.onNodeWithText("Compose").assertIsDisplayed()
    }

    @Test
    fun reposScreen_clickOnItem_invokesCallback() {
        val repo = Repo(
            id = 10L,
            name = "ClickMe",
            author = "Ada",
            description = "Sample",
            inFavorites = false
        )
        var clickedRepo: Repo? = null

        setReposScreen(
            state = ReposUiState.Success(repos = listOf(repo)),
            onRepoClick = { clickedRepo = it }
        )

        composeRule.onNodeWithText("ClickMe").performClick()

        composeRule.runOnIdle {
            assertEquals(repo, clickedRepo)
        }
    }

    @Test
    fun reposScreen_errorState_showsRefreshAndInvokesCallback() {
        var retryClicked = false

        setReposScreen(
            state = ReposUiState.Error(UiError(UiText.Plain("Error"))),
            onErrorActionClick = { retryClicked = true }
        )

        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, retryClicked)
        }
    }

    @Test
    fun reposScreen_emptyState_showsActionAndInvokesCallback() {
        var actionClicked = false

        setReposScreen(
            state = ReposUiState.Success(repos = emptyList()),
            onEmptyDataActionClick = { actionClicked = true }
        )

        composeRule.onNodeWithTag(EMPTY_DATA_ACTION_BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(EMPTY_DATA_ACTION_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, actionClicked)
        }
    }

    @Test
    fun reposScreen_loadingState_showsProgress() {
        setReposScreen(state = ReposUiState.Loading)

        composeRule.onNodeWithTag(REPOS_LOADING_TAG).assertIsDisplayed()
    }

    @Test
    fun reposScreen_loadingState_showsNoError() {
        setReposScreen(state = ReposUiState.Loading)

        composeRule.onAllNodesWithTag(ERROR_RETRY_BUTTON_TAG).assertCountEquals(0)
    }

    @Test
    fun reposScreen_searchClick_invokesCallback() {
        var searchClicked = false

        setReposScreen(
            state = ReposUiState.Success(repos = emptyList()),
            onSearchClick = { searchClicked = true }
        )

        composeRule.onNodeWithTag(REPOS_SEARCH_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, searchClicked)
        }
    }

    private fun setReposScreen(
        state: ReposUiState,
        onSearchClick: () -> Unit = {},
        onErrorActionClick: () -> Unit = {},
        onRefresh: () -> Unit = {},
        onEmptyDataActionClick: () -> Unit = {},
        onRepoClick: (Repo) -> Unit = {}
    ) {
        composeRule.setContent {
            AppTheme {
                ReposScreen(
                    state = state,
                    snackbarHostState = SnackbarHostState(),
                    onSearchClick = onSearchClick,
                    onSettingsClick = {},
                    onErrorActionClick = onErrorActionClick,
                    onRefresh = onRefresh,
                    onRepoClick = onRepoClick,
                    onEmptyDataActionClick = onEmptyDataActionClick
                )
            }
        }
    }
}
