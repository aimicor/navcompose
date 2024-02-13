package com.aimicor.navcompose.scaffoldandfullscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.aimicor.navcompose.android.composable
import com.aimicor.navcompose.android.navigate
import com.aimicor.navcompose.common.NavComposableSpec

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainNavGraph() }
    }
}

fun NavGraphBuilder.composableAnim(
    composableSpec: NavComposableSpec,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        composableSpec,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        popExitTransition = { ExitTransition.None },
        content = content
    )
}

fun NavHostController.jumpOutTo(
    otherNavController: NavHostController,
    otherRoute: NavComposableSpec
) {
    popBackStack(graph.findStartDestination().id, inclusive = false)
    otherNavController.navigate(otherRoute)
}
