package com.example.awesomearchsample

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awesomearchsample.feature.repo.repodetails.REPO_DETAILS_SCREEN_TAG
import com.example.awesomearchsample.feature.repo.repos.REPO_ITEM_TAG_PREFIX
import com.example.awesomearchsample.feature.repo.repos.REPOS_SEARCH_BUTTON_TAG
import com.example.awesomearchsample.feature.repo.repos.REPOS_SCREEN_TAG
import com.example.awesomearchsample.feature.search.SEARCH_SCREEN_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoNavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<AwesomeArchSampleActivity>()

    @Test
    fun appStart_clickRepoItem_navigatesToRepoDetails() {
        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithTag(REPOS_SCREEN_TAG).fetchSemanticsNodes().isNotEmpty()
        }

        val repoItemMatcher = SemanticsMatcher("hasRepoItemTag") { node ->
            val tag = node.config.getOrNull(SemanticsProperties.TestTag)
            tag?.startsWith(REPO_ITEM_TAG_PREFIX) == true
        }

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodes(repoItemMatcher).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onAllNodes(repoItemMatcher).onFirst().performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithTag(REPO_DETAILS_SCREEN_TAG).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag(REPO_DETAILS_SCREEN_TAG).assertExists()
    }

    @Test
    fun appStart_searchIconNavigatesToSearch() {
        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithTag(REPOS_SCREEN_TAG).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag(REPOS_SEARCH_BUTTON_TAG).performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithTag(SEARCH_SCREEN_TAG).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag(SEARCH_SCREEN_TAG).assertExists()
    }
}
