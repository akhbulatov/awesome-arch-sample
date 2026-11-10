package com.example.awesomearchsample

import androidx.activity.ComponentActivity
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
class LaunchStorageErrorTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun firstLaunchReadFails_opensMain() {
        composeRule.setContent(testAppContent(FakeAppSettingsRepository(failOnRead = true)))

        assertMainTitleDisplayed()
    }

    @Test
    fun firstLaunchWriteFails_opensMain() {
        composeRule.setContent(testAppContent(FakeAppSettingsRepository(failOnWrite = true)))

        assertMainTitleDisplayed()
    }

    private fun assertMainTitleDisplayed() {
        val title = composeRule.activity.getString(RepoR.string.repos_title)
        composeRule.waitUntilExactlyOneExists(hasText(title))
        composeRule.onNodeWithText(title).assertIsDisplayed()
    }
}
