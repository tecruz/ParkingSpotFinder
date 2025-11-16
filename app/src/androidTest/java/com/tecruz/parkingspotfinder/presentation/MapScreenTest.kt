package com.tecruz.parkingspotfinder.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.tecruz.parkingspotfinder.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MapScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mapScreen_initialState() {
        composeRule.onNodeWithContentDescription("Enable Fallout Map").assertIsDisplayed()
    }

    @Test
    fun toggleMapStyle_updatesIcon() {
        composeRule.onNodeWithContentDescription("Enable Fallout Map").performClick()
        composeRule.onNodeWithContentDescription("Disable Fallout Map").assertIsDisplayed()
    }
}