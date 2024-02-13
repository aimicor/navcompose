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

val screenInScaffold2 = navComposableSpec()

@Composable
fun ScreenInScaffold2(
    jumpTo: (NavComposableSpec) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 30.sp,
                color = Color.Red,
                text = "Screen in Scaffold 2"
            )
            Button(
                onClick = { jumpTo(fullscreen1) }
            ) {
                Text("Jump to fullscreen 1")
            }
            Button(
                onClick = { jumpTo(fullscreen2) }
            ) {
                Text("Jump to fullscreen 2")
            }
        }
    }
}

@Preview
@Composable
private fun Show() {
    ScreenInScaffold2{}
}