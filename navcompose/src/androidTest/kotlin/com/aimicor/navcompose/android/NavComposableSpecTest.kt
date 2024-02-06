package com.aimicor.navcompose.android

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.composable
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navParamSpec
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
@ExperimentalTestApi
@ExperimentalCoroutinesApi
class NavComposableSpecTest : NavComposableParent() {

    @Before
    fun setup() {
        mockStaticNavGraphBuilderFunction("composable")
    }

    @Test
    fun GIVEN_a_ComposableSpec_with_overridden_key_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val testKey = "gdfgd"
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val composableSpec = navComposableSpec(path = testKey)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = testKey,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
        }

    @Test
    fun WHEN_custom_composable_called_with_all_parameters_THEN_all_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val testKey = "gdfgd"
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val composableSpec = navComposableSpec(path = testKey)
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
            composable(
                composableSpec,
                deepLinks = testDeepLinks,
                enterTransition = testEnterTrans,
                exitTransition = testExitTrans,
                popEnterTransition = testPopEnterTrans,
                popExitTransition = testPopExitTrans,
                content = screen
            )

            // Then
            verify {
                composable(
                    route = testKey,
                    arguments = emptyList(),
                    deepLinks = testDeepLinks,
                    enterTransition = testEnterTrans,
                    exitTransition = testExitTrans,
                    popEnterTransition = testPopEnterTrans,
                    popExitTransition = testPopExitTrans,
                    content = screen
                )
            }
        }

    @Test
    fun WHEN_two_ComposableSpecs_are_created_with_the_factory_THEN_they_will_be_different() {
        // When
        val composableSpec1 = navComposableSpec()
        val composableSpec2 = navComposableSpec()

        // Then
        assertFalse { composableSpec1.path == composableSpec2.path }
    }

    @Test
    fun GIVEN_a_ComposableSpec_with_generated_key_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val composableSpec = navComposableSpec()

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = composableSpec.path,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
        }

    @Test
    fun GIVEN_a_ComposableSpec_with_a_nondefaulted_parameter_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val param = navParamSpec<Int>()
            val composableSpec = navComposableSpec(param)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = "${composableSpec.path}?${param.key}={${param.key}}",
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
        }

    @Test
    fun GIVEN_a_ComposableSpec_with_a_null_defaulted_parameter_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val param = navParamSpec<Int>()
            val argumentCapture = mutableListOf<List<NamedNavArgument>>()
            val composableSpec = navComposableSpec(param with null)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = "${composableSpec.path}?${param.key}={${param.key}}",
                    arguments = capture(argumentCapture),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
            argumentCapture.first().first().apply {
                assertEquals(param.key, name)
                assertNull(argument.defaultValue)
                assertTrue(argument.isNullable)
            }
        }

    @Test
    fun GIVEN_a_ComposableSpec_with_a_value_defaulted_parameter_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val param = navParamSpec<Int>()
            val argumentCapture = mutableListOf<List<NamedNavArgument>>()
            val composableSpec = navComposableSpec(param with 123)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = "${composableSpec.path}?${param.key}={${param.key}}",
                    arguments = capture(argumentCapture),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
            argumentCapture.first().first().apply {
                assertEquals(param.key, name)
                assertEquals(encode(123), argument.defaultValue)
                assertFalse(argument.isNullable)
            }

        }

    @Test
    fun GIVEN_a_ComposableSpec_with_two_nondefaulted_parameters_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val param1 = navParamSpec<Int>()
            val param2 = navParamSpec<String>()
            val composableSpec = navComposableSpec(param1, param2)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = "${composableSpec.path}?${param1.key}={${param1.key}}&${param2.key}={${param2.key}}",
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
        }

    @Test
    fun GIVEN_a_ComposableSpec_with_three_nondefaulted_parameters_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val param1 = navParamSpec<Int>()
            val param2 = navParamSpec<String>()
            val param3 = navParamSpec<Float>()
            val composableSpec = navComposableSpec(param1, param2, param3)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = "${composableSpec.path}?${param1.key}={${param1.key}}&${param2.key}={${param2.key}}&${param3.key}={${param3.key}}",
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
        }

    @Test
    fun GIVEN_a_ComposableSpec_with_mixed_parameters_nondefaulted_null_defaulted_and_value_defaulted_WHEN_custom_composable_called_THEN_correct_parameters_passed_to_underlying_composable() =
        testNav {
            // Given
            val screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit = {}
            val param1 = navParamSpec<Int>()
            val param2 = navParamSpec<String>()
            val param3 = navParamSpec<Boolean>()
            val argumentCapture = mutableListOf<List<NamedNavArgument>>()
            val testString = "123"
            val composableSpec = navComposableSpec(param1 with null, param2 with testString, param3)

            // When
            composable(composableSpec, content = screen)

            // Then
            verify {
                composable(
                    route = "${composableSpec.path}?${param1.key}={${param1.key}}&${param2.key}={${param2.key}}&${param3.key}={${param3.key}}",
                    arguments = capture(argumentCapture),
                    deepLinks = emptyList(),
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null,
                    content = screen
                )
            }
            assertEquals(1, argumentCapture.size)
            argumentCapture.first().apply {
                assertEquals(2, size)
                get(0).apply {
                    assertEquals(param1.key, name)
                    assertNull(argument.defaultValue)
                    assertTrue(argument.isNullable)
                }
                get(1).apply {
                    assertEquals(param2.key, name)
                    assertEquals(encode(testString), argument.defaultValue)
                    assertFalse(argument.isNullable)
                }
            }
        }
}
