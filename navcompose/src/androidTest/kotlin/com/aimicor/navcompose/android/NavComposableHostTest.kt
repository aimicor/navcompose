package com.aimicor.navcompose.android

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.rememberNavController
import com.aimicor.navcompose.common.navComposableSpec
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NavComposableHostTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun GIVEN_custom_NavHost_WHEN_called_with_all_parameters_THEN_underlying_NavHost_called_with_all_parameters() {
        // Given
        val testModifier = Modifier
        val testContentAlignment: Alignment = Alignment.Center
        val testEnterTrans: @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = { fadeIn() }
        val testExitTrans: @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = { fadeOut() }
        val testPopEnterTrans: @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = { fadeIn() }
        val testPopExitTrans: @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = { fadeOut() }

        var isChecked = false

        composeTestRule.setContent {
            val composableSpec = navComposableSpec()
            val navController = rememberNavController()
            val builder: NavGraphBuilder.() -> Unit = {
                composable(composableSpec) {}
                isChecked = true
            }

            NavHost(
                navController = navController,
                startDestination = composableSpec,
                modifier = testModifier,
                route = "something",
                contentAlignment = testContentAlignment,
                enterTransition = testEnterTrans,
                exitTransition = testExitTrans,
                popEnterTransition = testPopEnterTrans,
                popExitTransition = testPopExitTrans,
                builder = builder
            )

            // When
            navController.navigate(composableSpec)

            // Then
            assertTrue { isChecked }
            // TODO currently no way to test NavHost received all parameters
            // (mockposable won't build with Compose multiplatform)
        }
    }

    @Test
    fun GIVEN_a_simple_valid_NavHost_WHEN_navigate_called_THEN_nav_builder_lambda_is_executed() {
        // Given
        val startDestinationField =
            NavGraphBuilder::class.java.getDeclaredField("startDestinationRoute")
                .apply { isAccessible = true }

        var isChecked = false

        composeTestRule.setContent {
            val composableSpec = navComposableSpec()
            val navController = rememberNavController()
            val builder: NavGraphBuilder.() -> Unit = {
                assertEquals(composableSpec.route, startDestinationField.get(this))
                composable(composableSpec) {
                    assertEquals(navController, LocalNavController.current)
                }
                isChecked = true
            }

            NavHost(
                navController = navController,
                startDestination = composableSpec,
                builder = builder
            )

            // When
            navController.navigate(composableSpec)

            // Then
            assertTrue { isChecked }
        }
    }
}

