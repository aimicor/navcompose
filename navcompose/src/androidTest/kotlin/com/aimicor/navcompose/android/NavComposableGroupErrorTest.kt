package com.aimicor.navcompose.android

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navParamSpec
import org.junit.Rule
import org.junit.Test

class NavComposableGroupErrorTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test(expected = StartIsNotDirectChildException::class)
    fun GIVEN_NavHost_start_is_not_a_direct_child_WHEN_NavHost_called_THEN_exception_thrown() {
        // Given
        val navComposableSpec = navComposableSpec()
        val innerComposableGroup =
            navComposableGroup(startDestination = navComposableSpec) {}
        val outerComposableGroup =
            navComposableGroup(startDestination = navComposableSpec) {
                navigation(innerComposableGroup)
            }

        composeTestRule.setContent {
            val navController = rememberNavController()

            // When
            NavHost(
                navController = navController,
                startDestination = outerComposableGroup
            ) {
                navigation(outerComposableGroup)
            }
        }
    }

    @Test(expected = StartHasNonDefaultedParamsException::class)
    fun GIVEN_NavHost_start_is_Composable_that_takes_non_defaulted_params_WHEN_NavHost_called_THEN_exception_thrown() {
        // Given
        val navComposableSpec = navComposableSpec(navParamSpec<Int>())
        val outerComposableGroup =
            navComposableGroup(startDestination = navComposableSpec) { composable(navComposableSpec) {} }

        composeTestRule.setContent {
            val navController = rememberNavController()

            // When
            NavHost(
                navController = navController,
                startDestination = outerComposableGroup
            ) {
                navigation(outerComposableGroup)
            }
        }
    }

    @Test
    fun GIVEN_NavHost_start_is_Composable_that_takes_defaulted_params_WHEN_NavHost_called_THEN_exception_not_thrown() {
        // Given
        val navComposableSpec = navComposableSpec(navParamSpec<Int>() with 0)
        val outerComposableGroup =
            navComposableGroup(startDestination = navComposableSpec) { composable(navComposableSpec) {} }

        composeTestRule.setContent {
            val navController = rememberNavController()

            // When
            NavHost(
                navController = navController,
                startDestination = outerComposableGroup
            ) {
                navigation(outerComposableGroup)
            }
        }
    }
}