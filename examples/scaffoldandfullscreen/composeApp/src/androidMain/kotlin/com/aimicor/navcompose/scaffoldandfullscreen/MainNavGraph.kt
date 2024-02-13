package com.aimicor.navcompose.scaffoldandfullscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.aimicor.navcompose.android.NavHost
import com.aimicor.navcompose.android.composable
import com.aimicor.navcompose.android.navigate
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.scaffoldandfullscreen.scaffold.Scaffolder
import com.aimicor.navcompose.scaffoldandfullscreen.screens.Fullscreen1
import com.aimicor.navcompose.scaffoldandfullscreen.screens.Fullscreen2
import com.aimicor.navcompose.scaffoldandfullscreen.screens.ScreenInScaffold1
import com.aimicor.navcompose.scaffoldandfullscreen.screens.ScreenInScaffold2
import com.aimicor.navcompose.scaffoldandfullscreen.screens.fullscreen1
import com.aimicor.navcompose.scaffoldandfullscreen.screens.fullscreen2
import com.aimicor.navcompose.scaffoldandfullscreen.screens.screenInScaffold1
import com.aimicor.navcompose.scaffoldandfullscreen.screens.screenInScaffold2

@Composable
fun MainNavGraph() {
    val fullScreenNavController = rememberNavController()
    val scaffoldNavController = rememberNavController()
    val scaffoldComposable = navComposableSpec()
    NavHost( // outer NavHost
        fullScreenNavController,
        startDestination = scaffoldComposable
    ) {
        composable(fullscreen1) {
            Fullscreen1(
                { fullScreenNavController.jumpOutTo(scaffoldNavController, it) },
                { fullScreenNavController.navigate(it) }
            )
        }
        composable(fullscreen2) {
            Fullscreen2 (
                { fullScreenNavController.jumpOutTo(scaffoldNavController, it) },
                { fullScreenNavController.navigate(it) }
            )
        }
        composable(scaffoldComposable) {
            Scaffolder(
                destination = scaffoldNavController
                    .currentBackStackEntryFlow
                    .collectAsState(scaffoldNavController.currentBackStackEntry)
                    .value?.destination?.route,
                navigate = { scaffoldNavController.navigate(it) }
            ) {
                NavHost( // inner NavHost
                    scaffoldNavController,
                    startDestination = screenInScaffold1
                ) {
                    composableAnim(screenInScaffold1) {
                        ScreenInScaffold1 {
                            scaffoldNavController.jumpOutTo(fullScreenNavController, it)
                        }
                    }
                    composableAnim(screenInScaffold2) {
                        ScreenInScaffold2 {
                            scaffoldNavController.jumpOutTo(fullScreenNavController, it)
                        }
                    }
                }
            }
        }
    }
}
