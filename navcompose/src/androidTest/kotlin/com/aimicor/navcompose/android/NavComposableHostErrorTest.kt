package com.aimicor.navcompose.android

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navParamSpec
import org.junit.Rule
import org.junit.Test

class NavComposableHostErrorTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test(expected = StartIsNotDirectChildException::class)
    fun GIVEN_a_start_destination_which_is_not_a_direct_child_WHEN_NavHost_called_THEN_exception_thrown() {
        // Given
        val innerComposableGroup =
            navComposableGroup(startDestination = navComposableSpec()) {}
        val outerComposableGroup =
            navComposableGroup(startDestination = innerComposableGroup) {}

        composeTestRule.setContent {
            val navController = rememberNavController()

            // When
            NavHost(
                navController = navController,
                startDestination = innerComposableGroup
            ) {
                navigation(outerComposableGroup)
            }
        }
    }

    @Test(expected = StartHasNonDefaultedParamsException::class)
    fun GIVEN_a_start_destination_which_has_non_defaulted_parameters_WHEN_NavHost_called_THEN_exception_thrown() {
        // Given
        val start = navComposableSpec(navParamSpec<Int>())

        composeTestRule.setContent {
            val navController = rememberNavController()

            // When
            NavHost(
                navController = navController,
                startDestination = start
            ) {
                composable(start) {}
            }
        }
    }

    @Test
    fun GIVEN_a_start_destination_which_has_defaulted_parameters_WHEN_NavHost_called_THEN_exception_not_thrown() {
        // Given
        val start = navComposableSpec(navParamSpec<Int>() with 0)

        composeTestRule.setContent {
            val navController = rememberNavController()

            // When
            NavHost(
                navController = navController,
                startDestination = start
            ) {
                composable(start) {}
            }
        }
    }
}