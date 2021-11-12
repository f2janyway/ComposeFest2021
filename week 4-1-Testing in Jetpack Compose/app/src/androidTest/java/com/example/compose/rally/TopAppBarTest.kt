package com.example.compose.rally

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import androidx.test.espresso.Espresso.pressBack
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var currentScreen:MutableState<RallyScreen>
    @Before
    fun setup() {
        val allScreen = RallyScreen.values().toList()
        composeTestRule.setContent {

             currentScreen = remember {
                mutableStateOf(RallyScreen.Accounts)
            }
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreen,
                    onTabSelected = {
                        currentScreen.value = it
                    },
                    currentScreen = currentScreen.value
                )
            }
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("rallyTopAppBar before")
    }

    @Test
    fun rallyTopAppBar_accountSelectedTest() {
        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()

    }

    @Test
    fun rallyTopAppBar_currentLabelExists() {
//        composeTestRule
//            .onNode(
//                hasText(RallyScreen.Accounts.name.uppercase()) and
//                        hasParent(
//                            hasContentDescription(RallyScreen.Accounts.name)
//                        ),
//                useUnmergedTree = true
//            )
//            .assertIsDisplayed()
        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase())
//                        and hasParent(
//                            hasContentDescription(RallyScreen.Accounts.name)
//                        ),
                ,
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }

    @Test
    fun rallyTopAppBar_overViewSelectedTest() {
        composeTestRule.run {
            onNodeWithContentDescription(RallyScreen.Overview.name)
                .performClick()

            onNodeWithContentDescription(RallyScreen.Overview.name)
                .assertIsSelected()

            onRoot(useUnmergedTree = true).printToLog("rallyTopAppBar overViewSelected")

            onNode(
                hasText(RallyScreen.Overview.name.uppercase()), useUnmergedTree = true
            ).assertExists()
        }
    }

    @Test
    fun rallyTopAppBar_selectStateTest(){
        composeTestRule.run {
            onNodeWithContentDescription(RallyScreen.Overview.name)
                .performClick()

            assertThat(currentScreen.value).isEqualTo(RallyScreen.Overview)

            onNodeWithContentDescription(RallyScreen.Bills.name)
                .performClick()

            assertThat(currentScreen.value).isEqualTo(RallyScreen.Bills)
        }
    }
}