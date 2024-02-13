package com.aimicor.navcompose.scaffoldandfullscreen.scaffold

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import com.aimicor.navcompose.common.NavComposableSpec
import com.aimicor.navcompose.scaffoldandfullscreen.screens.screenInScaffold1
import com.aimicor.navcompose.scaffoldandfullscreen.screens.screenInScaffold2

@Composable
fun BottomBar(
    destination: String?,
    navigate: (NavComposableSpec) -> Unit
) {
    BottomAppBar(
        content = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, "") },
                    label = { Text("Home") },
                    selected = destination == screenInScaffold1.route,
                    onClick = { navigate(screenInScaffold1) }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, "") },
                    label = { Text("Favorites") },
                    selected = destination == screenInScaffold2.route,
                    onClick = { navigate(screenInScaffold2) }
                )
            }
        }
    )
}