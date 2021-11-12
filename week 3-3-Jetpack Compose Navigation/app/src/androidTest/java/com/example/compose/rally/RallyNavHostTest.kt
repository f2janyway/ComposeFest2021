package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RallyNavHostTest {
    @get:Rule
    val composableRule = createComposeRule()
    lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {
        composableRule.setContent {
            navController = rememberNavController()
            RallyNavHost(navController = navController)
        }
    }
    @Test
    fun rallyNavHost(){

        composableRule.onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }
    @Test
    fun rallyNavHost_navigateToAllAccounts_viaUI() {
        composableRule
            .onNodeWithContentDescription("All Accounts")
            .performClick()
        composableRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_navigateToBills_viaUI(){
        composableRule.onNodeWithContentDescription("All Bills").apply {
            performScrollTo()
            performClick()
        }
//        val route = navController.currentBackStackEntry?.destination?.route
//        assertThat(route).isEqualTo("Bills")
    }

    @Test
    fun rallyNavHost_navigateToAllAccounts_callingNavigate() {
        runBlocking {
            withContext(Dispatchers.Main) {
                navController.navigate(RallyScreen.Accounts.name)
            }
        }
        composableRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }
}