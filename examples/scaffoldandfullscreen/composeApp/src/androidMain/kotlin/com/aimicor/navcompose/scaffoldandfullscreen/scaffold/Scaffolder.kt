package com.aimicor.navcompose.scaffoldandfullscreen.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aimicor.navcompose.common.NavComposableSpec

@Composable
fun Scaffolder(
    destination: String?,
    navigate: (NavComposableSpec) -> Unit,
    content: @Composable () -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        bottomBar = { BottomBar(destination, navigate) },
        drawerContent = { DrawerContent() },
        floatingActionButton = { Fab(scaffoldState.snackbarHostState, navigate, destination) },
        snackbarHost = { SnackHost(scaffoldState.snackbarHostState) }
    ) { Box(modifier = Modifier.padding(it)) { content() } }
}
