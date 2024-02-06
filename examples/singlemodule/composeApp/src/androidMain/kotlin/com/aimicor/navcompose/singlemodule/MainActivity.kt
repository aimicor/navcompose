package com.aimicor.navcompose.singlemodule

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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.rememberNavController
import com.aimicor.navcompose.android.NavHost
import com.aimicor.navcompose.android.composable
import com.aimicor.navcompose.android.getNavParam
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navParamSpec

val param1 = navParamSpec<ComplexStuff>()
val param2 = navParamSpec<ComplexStuff>()
val param3 = navParamSpec<ComplexStuff>()
val homescreen = navComposableSpec()

val detailScreen = navComposableSpec(
    param1, // compulsory parameter (no default specified)
    param2 with ComplexStuff("second"), // default
    param3 with null // default
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = homescreen,
                route = "random",
                enterTransition = {
                    if(targetState.destination.route == detailScreen.route)
                        slideInHorizontally()
                    else EnterTransition.None
                },
                exitTransition = { ExitTransition.None },
                popExitTransition = {
                    if(targetState.destination.route == homescreen.route)
                        slideOutHorizontally()
                    else ExitTransition.None
                }
            ) {
                composable(homescreen) { HomeScreen() }
                composable(detailScreen) { backStackEntry ->

                    // fetch parameter from backStackEntry
                    DetailScreen(backStackEntry.getNavParam(param2))
                }
            }
        }
    }
}
