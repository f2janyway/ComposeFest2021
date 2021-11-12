package com.example.compose.rally

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test


class TopAppBarTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest(){
        composeTestRule.setContent {
            RallyTheme {
            }
        }
    }
}