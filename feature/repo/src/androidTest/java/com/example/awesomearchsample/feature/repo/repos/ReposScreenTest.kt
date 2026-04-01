package com.example.awesomearchsample.feature.repo.repos

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.EMPTY_DATA_ACTION_BUTTON_TAG
import com.example.awesomearchsample.core.ui.designsystem.ERROR_RETRY_BUTTON_TAG
import com.example.awesomearchsample.core.ui.designsystem.UiEmptyData
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

        setReposContent(
            state = ReposUiState(
                data = ReposUiData(repos = repos),
                isInitialLoading = false
            )
        )

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

        setReposContent(
            state = ReposUiState(
                data = ReposUiData(repos = listOf(repo)),
                isInitialLoading = false
            ),
            onRepoClick = { clickedRepo = it }
        )

        composeRule.onNodeWithText("ClickMe").performClick()

        composeRule.runOnIdle {
            assertEquals(repo, clickedRepo)
        }
    }

    @Test
    fun reposContent_errorState_showsRefreshAndInvokesCallback() {
        var retryClicked = false

        setReposContent(
            state = ReposUiState(
                isInitialLoading = false,
                initialError = UiError(UiText.Plain("Error"))
            ),
            onErrorActionClick = { retryClicked = true }
        )

        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, retryClicked)
        }
    }

    @Test
    fun reposContent_emptyState_showsActionAndInvokesCallback() {
        var actionClicked = false

        setReposContent(
            state = ReposUiState(
                isInitialLoading = false,
                initialEmptyData = UiEmptyData(
                    title = UiText.Plain("Empty"),
                    actionText = UiText.Plain("Refresh")
                )
            ),
            onEmptyDataActionClick = { actionClicked = true }
        )

        composeRule.onNodeWithText("Empty").assertIsDisplayed()
        composeRule.onNodeWithTag(EMPTY_DATA_ACTION_BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(EMPTY_DATA_ACTION_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, actionClicked)
        }
    }

    @Test
    fun reposContent_loadingState_showsProgress() {
        setReposContent(state = ReposUiState(isInitialLoading = true))

        composeRule.onNodeWithTag(REPOS_LOADING_TAG).assertIsDisplayed()
    }

    @Test
    fun reposContent_loadingState_showsNoError() {
        setReposContent(state = ReposUiState(isInitialLoading = true))

        composeRule.onAllNodesWithTag(ERROR_RETRY_BUTTON_TAG).assertCountEquals(0)
    }

    @Test
    fun reposContent_searchClick_invokesCallback() {
        var searchClicked = false

        setReposContent(
            state = ReposUiState(
                data = ReposUiData(repos = emptyList()),
                isInitialLoading = false
            ),
            onSearchClick = { searchClicked = true }
        )

        composeRule.onNodeWithTag(REPOS_SEARCH_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, searchClicked)
        }
    }

    @Test
    fun reposContent_successWithEmptyList_doesNotShowList() {
        setReposContent(
            state = ReposUiState(
                data = ReposUiData(repos = emptyList()),
                isInitialLoading = false
            )
        )

        composeRule.onAllNodesWithTag(REPOS_LIST_TAG).assertCountEquals(0)
    }

    private fun setReposContent(
        state: ReposUiState,
        onSearchClick: () -> Unit = {},
        onErrorActionClick: () -> Unit = {},
        onEmptyDataActionClick: () -> Unit = {},
        onRepoClick: (Repo) -> Unit = {}
    ) {
        composeRule.setContent {
            AppTheme {
                ReposContent(
                    state = state,
                    snackbarHostState = SnackbarHostState(),
                    onSearchClick = onSearchClick,
                    onSettingsClick = {},
                    onErrorActionClick = onErrorActionClick,
                    onEmptyDataActionClick = onEmptyDataActionClick,
                    onRefresh = {},
                    onRepoClick = onRepoClick
                )
            }
        }
    }
}
