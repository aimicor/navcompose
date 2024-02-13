package com.aimicor.navcompose.scaffoldandfullscreen.scaffold

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.aimicor.navcompose.common.NavComposableSpec
import com.aimicor.navcompose.scaffoldandfullscreen.screens.screenInScaffold1
import com.aimicor.navcompose.scaffoldandfullscreen.screens.screenInScaffold2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Fab(
    snackState: SnackbarHostState,
    navigate: (NavComposableSpec) -> Unit,
    destination: String?
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                snackState.showSnackbar(
                    message = "Snack Bar",
                    actionLabel = "Navigate",
                    duration = SnackbarDuration.Indefinite
                ).let {result -> switchScreens(result, navigate, destination) }
            }
        },
        content = { Icon(Icons.Default.Add, "") }
    )
}

private fun switchScreens(
    result: SnackbarResult,
    navigate: (NavComposableSpec) -> Unit,
    destination: String?
) {
    when (result) {
        SnackbarResult.Dismissed -> {}
        SnackbarResult.ActionPerformed -> navigate(
            if (destination == screenInScaffold1.route) screenInScaffold2
            else screenInScaffold1
        )
    }
}