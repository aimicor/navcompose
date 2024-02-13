package com.aimicor.navcompose.scaffoldandfullscreen.scaffold

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Top App Bar") },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, "")
            }
        }
    )
}