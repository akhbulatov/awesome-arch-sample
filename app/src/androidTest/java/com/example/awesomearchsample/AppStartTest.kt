package com.example.awesomearchsample

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.awesomearchsample.feature.repo.R as RepoR

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class AppStartTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<AwesomeArchSampleActivity>()

    @Test
    fun appStart_showsMainReposTitle() {
        val title = composeRule.activity.getString(RepoR.string.repos_title)
        composeRule.waitUntilExactlyOneExists(hasText(title), timeoutMillis = 5_000)
        composeRule.onNodeWithText(title).assertIsDisplayed()
    }
}
