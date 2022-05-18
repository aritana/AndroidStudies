package com.example.compose.rally

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = ,
                onTabSelected = { /*TODO*/ },
                currentScreen =
            )
        }
    }
}