package com.aimicor.navcompose.scaffoldandfullscreen.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aimicor.navcompose.common.NavComposableSpec
import com.aimicor.navcompose.common.navComposableSpec

val fullscreen1 = navComposableSpec()

@Composable
fun Fullscreen1(
    jumpTo: (NavComposableSpec) -> Unit,
    navigate: (NavComposableSpec) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 30.sp,
                color = Color.Green,
                text = "Full Screen 1"
            )
            Button(
                onClick = { navigate(fullscreen2) }
            ) {
                Text("Navigate to full screen 2")
            }
            Button(
                onClick = { jumpTo(screenInScaffold1) }
            ) {
                Text("Jump to scaffold 1")
            }
            Button(
                onClick = { jumpTo(screenInScaffold2) }
            ) {
                Text("Jump to scaffold 2")
            }
        }
    }
}

@Preview
@Composable
private fun Show() {
    Fullscreen1({},{})
}