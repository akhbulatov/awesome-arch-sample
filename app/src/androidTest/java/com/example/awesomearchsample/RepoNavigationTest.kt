package com.example.awesomearchsample

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awesomearchsample.feature.repo.repodetails.REPO_DETAILS_AUTHOR_TAG
import com.example.awesomearchsample.feature.repo.repodetails.REPO_DETAILS_SCREEN_TAG
import com.example.awesomearchsample.feature.repo.repos.REPOS_SEARCH_BUTTON_TAG
import com.example.awesomearchsample.feature.repo.repos.component.REPO_ITEM_TAG_PREFIX
import com.example.awesomearchsample.feature.search.SEARCH_SCREEN_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class RepoNavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun clickRepoItem_opensDetailsAndBackReturnsToRepos() {
        composeRule.setContent(testAppContent())

        openRepoDetails()
        composeRule.onNodeWithTag(REPO_DETAILS_SCREEN_TAG).assertIsDisplayed()

        pressBack()
        composeRule.onNodeWithTag(repoItemTag).assertIsDisplayed()
    }

    @Test
    fun clickSearchIcon_opensSearch() {
        composeRule.setContent(testAppContent())
        composeRule.waitUntilExactlyOneExists(hasTestTag(REPOS_SEARCH_BUTTON_TAG))

        composeRule.onNodeWithTag(REPOS_SEARCH_BUTTON_TAG).performClick()

        composeRule.onNodeWithTag(SEARCH_SCREEN_TAG).assertIsDisplayed()
    }

    @Test
    fun clickRepoAuthor_opensUserDetails() {
        composeRule.setContent(testAppContent())

        openUserDetails()

        composeRule.onNodeWithText(TEST_AUTHOR_NAME).assertIsDisplayed()
    }

    @Test
    fun restoreSavedState_preservesUserDetailsAndItsBackStack() {
        val restorationTester = StateRestorationTester(composeRule)
        restorationTester.setContent(testAppContent())
        openUserDetails()

        restorationTester.emulateSavedInstanceStateRestore()

        composeRule.waitUntilExactlyOneExists(hasText(TEST_AUTHOR_NAME))
        composeRule.onNodeWithText(TEST_AUTHOR_NAME).assertIsDisplayed()
        pressBack()
        composeRule.onNodeWithTag(REPO_DETAILS_SCREEN_TAG).assertIsDisplayed()
        pressBack()
        composeRule.onNodeWithTag(repoItemTag).assertIsDisplayed()
    }

    private val repoItemTag = REPO_ITEM_TAG_PREFIX + TEST_REPO_ID

    private fun openRepoDetails() {
        composeRule.waitUntilExactlyOneExists(hasTestTag(repoItemTag))
        composeRule.onNodeWithTag(repoItemTag).performClick()
    }

    private fun openUserDetails() {
        openRepoDetails()
        composeRule.waitUntilExactlyOneExists(hasTestTag(REPO_DETAILS_AUTHOR_TAG))
        composeRule.onNodeWithTag(REPO_DETAILS_AUTHOR_TAG).performClick()
        composeRule.waitUntilExactlyOneExists(hasText(TEST_AUTHOR_NAME))
    }

    private fun pressBack() {
        val activity = composeRule.activity
        composeRule.runOnIdle { activity.onBackPressedDispatcher.onBackPressed() }
    }
}
