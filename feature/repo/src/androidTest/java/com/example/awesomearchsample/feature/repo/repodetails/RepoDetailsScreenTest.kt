package com.example.awesomearchsample.feature.repo.repodetails

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.core.ui.designsystem.ERROR_RETRY_BUTTON_TAG
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.RepoDetails
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDetailsScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun repoDetailsContent_success_showsBasicInfo() {
        val details = RepoDetails(
            id = 1L,
            name = "AwesomeRepo",
            author = "Ada",
            description = "Sample",
            starsCount = 99,
            forksCount = 10
        )

        composeRule.setContent {
            AppTheme {
                RepoDetailsContent(
                    state = RepoDetailsUiState.Success(repoDetails = details),
                    onNavigationClick = {},
                    onErrorActionClick = {},
                    onAuthorClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(REPO_DETAILS_NAME_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(REPO_DETAILS_AUTHOR_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(REPO_DETAILS_STARS_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(REPO_DETAILS_FORKS_TAG).assertIsDisplayed()
    }

    @Test
    fun repoDetailsContent_authorClick_invokesCallback() {
        val details = RepoDetails(
            id = 1L,
            name = "AwesomeRepo",
            author = "Ada",
            description = "Sample",
            starsCount = 99,
            forksCount = 10
        )
        var authorClicked = false

        composeRule.setContent {
            AppTheme {
                RepoDetailsContent(
                    state = RepoDetailsUiState.Success(repoDetails = details),
                    onNavigationClick = {},
                    onErrorActionClick = {},
                    onAuthorClick = { authorClicked = true }
                )
            }
        }

        composeRule.onNodeWithTag(REPO_DETAILS_AUTHOR_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, authorClicked)
        }
    }

    @Test
    fun repoDetailsContent_backClick_invokesCallback() {
        val details = RepoDetails(
            id = 1L,
            name = "AwesomeRepo",
            author = "Ada",
            description = "Sample",
            starsCount = 99,
            forksCount = 10
        )
        var backClicked = false

        composeRule.setContent {
            AppTheme {
                RepoDetailsContent(
                    state = RepoDetailsUiState.Success(repoDetails = details),
                    onNavigationClick = { backClicked = true },
                    onErrorActionClick = {},
                    onAuthorClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(REPO_DETAILS_BACK_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, backClicked)
        }
    }

    @Test
    fun repoDetailsContent_loading_showsProgress() {
        composeRule.setContent {
            AppTheme {
                RepoDetailsContent(
                    state = RepoDetailsUiState.Loading,
                    onNavigationClick = {},
                    onErrorActionClick = {},
                    onAuthorClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(REPO_DETAILS_LOADING_TAG).assertIsDisplayed()
    }

    @Test
    fun repoDetailsContent_error_showsRetryAndInvokesCallback() {
        var retryClicked = false

        composeRule.setContent {
            AppTheme {
                RepoDetailsContent(
                    state = RepoDetailsUiState.Error(
                        error = UiError(UiText.Plain("Error"))
                    ),
                    onNavigationClick = {},
                    onErrorActionClick = { retryClicked = true },
                    onAuthorClick = {}
                )
            }
        }

        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(ERROR_RETRY_BUTTON_TAG).performClick()

        composeRule.runOnIdle {
            assertEquals(true, retryClicked)
        }
    }
}
