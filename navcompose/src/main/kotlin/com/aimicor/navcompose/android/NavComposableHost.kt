package com.aimicor.navcompose.android

/*
MIT License

Copyright (c) 2024 Aimicor Ltd. 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aimicor.navcompose.common.NavComposable
import com.aimicor.navcompose.common.NavComposableGroup

/**
 * Specifies the root of the navigation graph.
 * @param navController is the object that controls navigation.
 * @param startDestination object that specifies the initial entry point in
 * this graph root.
 * @param modifier The modifier to be applied to the layout. See [Modifier]
 * @param contentAlignment The [Alignment] of the [AnimatedContent]
 * @param enterTransition see [EnterTransition]
 * @param exitTransition see [ExitTransition]
 * @param popEnterTransition see [EnterTransition]
 * @param popExitTransition see [ExitTransition]
 * @param builder lambda specifying the navigation graph.
 * @see androidx.navigation.compose.NavHost
 */
@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: NavComposable,
    modifier: Modifier = Modifier,
    route: String? = null,
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(700)) },
    exitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(700)) },
    popEnterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        exitTransition,
    builder: NavGraphBuilder.() -> Unit
) = navComposableGroup(
    startDestination = startDestination,
    builder = builder
).let { group ->
    NavHost(
        navController,
        group,
        modifier = modifier,
        route = route,
        contentAlignment = contentAlignment,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    )
}

/**
 * Specifies the root of the navigation graph.
 * @param navController is the object that controls navigation.
 * @param group object specifying a sub graph of composables and / or
 * other sub graphs.
 * @see androidx.navigation.compose.NavHost
 */
@Composable
fun NavHost(
    navController: NavHostController,
    group: NavComposableGroup,
    modifier: Modifier = Modifier,
    route: String? = null,
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(700)) },
    exitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(700)) },
    popEnterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        exitTransition,
) {
    group.assertStart(navController.navigatorProvider)

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = group.startDestination.route,
            modifier = modifier,
            route = route,
            contentAlignment = contentAlignment,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            builder = group.builder
        )
    }
}
