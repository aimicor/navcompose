package com.aimicor.navcompose.android

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navGroupRoute
import com.aimicor.navcompose.common.navParamSpec
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalTestApi
@ExperimentalCoroutinesApi
class NavComposableGroupTest : NavComposableParent() {

    @Before
    fun setup() {
        mockStaticNavGraphBuilderFunction("navigation")
    }

    @Test
    fun GIVEN_a_group_starting_with_a_single_screen_with_no_parameters_WHEN_navigation_called_THEN_correct_parameters_passed_to_underlying_navigation() =
        testNav {
            // Given
            val composableKey = "sdfsdf"
            val navigationKey = "fsfs"
            val navGroupRoute = navGroupRoute(navigationKey)
            val composableSpec = navComposableSpec(path = composableKey)
            val builder: NavGraphBuilder.() -> Unit = { composable(composableSpec) {} }
            val composableGroup =
                navComposableGroup(
                    startDestination = composableSpec,
                    groupPath = navGroupRoute,
                    builder
                )

            // When
            navigation(group = composableGroup)

            // Then
            verify {
                navigation(
                    startDestination = composableKey,
                    route = navigationKey,
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    builder = builder
                )
            }
        }

    @Test
    fun GIVEN_a_group_called_with_all_parameters_WHEN_navigation_called_THEN_correct_parameters_passed_to_underlying_navigation() =
        testNav {
            // Given
            val composableKey = "sdfsdf"
            val navigationKey = "fsfs"
            val navGroupRoute = navGroupRoute(navigationKey)
            val composableSpec = navComposableSpec(path = composableKey)
            val builder: NavGraphBuilder.() -> Unit = { composable(composableSpec) {} }
            val composableGroup =
                navComposableGroup(
                    startDestination = composableSpec,
                    groupPath = navGroupRoute,
                    builder
                )
            val testDeepLinks: List<NavDeepLink> = listOf(NavDeepLink(""))
            val testEnterTrans: @JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = { fadeIn() }
            val testExitTrans: @JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = { fadeOut() }
            val testPopEnterTrans: @JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = { fadeIn() }
            val testPopExitTrans: @JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = { fadeOut() }

            // When
            navigation(
                group = composableGroup,
                deepLinks = testDeepLinks,
                enterTransition = testEnterTrans,
                exitTransition = testExitTrans,
                popEnterTransition = testPopEnterTrans,
                popExitTransition = testPopExitTrans,
            )

            // Then
            verify {
                navigation(
                    startDestination = composableKey,
                    route = navigationKey,
                    deepLinks = testDeepLinks,
                    enterTransition = testEnterTrans,
                    exitTransition = testExitTrans,
                    popEnterTransition = testPopEnterTrans,
                    popExitTransition = testPopExitTrans,
                    builder = builder
                )
            }
        }

    @Test
    fun GIVEN_a_group_starting_with_another_group_WHEN_navigation_called_THEN_correct_parameters_passed_to_underlying_navigation() =
        testNav {
            // Given
            val composableKey = "sdfsdf"
            val innerNavigationKey = "fsfs"
            val outerNavigationKey = "dfgdgd"
            val innerNavGroupPath = navGroupRoute(innerNavigationKey)
            val outerNavGroupPath = navGroupRoute(outerNavigationKey)
            val composableSpec = navComposableSpec(path = composableKey)
            val innerComposableGroup =
                navComposableGroup(
                    startDestination = composableSpec,
                    groupPath = innerNavGroupPath
                ) {
                    composable(composableSpec) {}
                }
            val outerBuilder: NavGraphBuilder.() -> Unit =
                { navigation(group = innerComposableGroup) }
            val outerComposableGroup =
                navComposableGroup(
                    startDestination = innerComposableGroup,
                    groupPath = outerNavGroupPath,
                    outerBuilder
                )

            // When
            navigation(group = outerComposableGroup)

            // Then
            verify {
                navigation(
                    startDestination = innerNavigationKey,
                    route = outerNavigationKey,
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    builder = outerBuilder
                )
            }
        }

    @Test
    fun GIVEN_a_group_starting_with_a_single_screen_with_defaulted_parameters_WHEN_navigation_called_THEN_correct_parameters_passed_to_underlying_navigation() =
        testNav {
            // Given
            val param1 = navParamSpec<Int>()
            val param2 = navParamSpec<String>()
            val param3 = navParamSpec<Boolean>()
            val testString = "123"
            val composableSpec =
                navComposableSpec(param1 with null, param2 with testString, param3 with false)
            val builder: NavGraphBuilder.() -> Unit = { composable(composableSpec) {} }
            val composableGroup =
                navComposableGroup(startDestination = composableSpec, builder = builder)

            // When
            navigation(group = composableGroup)

            // Then
            verify {
                navigation(
                    startDestination = "${composableSpec.path}?${param1.key}={${param1.key}}&${param2.key}={${param2.key}}&${param3.key}={${param3.key}}",
                    route = composableGroup.route,
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    builder = builder
                )
            }
        }
}