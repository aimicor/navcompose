package com.aimicor.navcompose.scaffoldandfullscreen.scaffold

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SnackHost(state: SnackbarHostState) {
    SnackbarHost(state) { data ->
        Snackbar(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.primary,
            snackbarData = data
        )
    }
}