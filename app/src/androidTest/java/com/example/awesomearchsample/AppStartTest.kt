package com.example.awesomearchsample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.example.awesomearchsample.feature.repo.repos.REPOS_SCREEN_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class AppStartTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<AwesomeArchSampleActivity>()

    @Test
    fun appStart_showsMainReposTitle() {
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(REPOS_SCREEN_TAG).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag(REPOS_SCREEN_TAG).assertIsDisplayed()
    }
}
