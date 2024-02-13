package com.aimicor.navcompose.scaffoldandfullscreen.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .padding(40.dp)
            .height(500.dp)
    ) {
        Button(onClick = { }) { Text("navigate") }
        Button(onClick = { }) { Text("navigate") }
        Button(onClick = { }) { Text("navigate") }
        Button(onClick = { }) { Text("navigate") }
        Button(onClick = { }) { Text("navigate") }
        Button(onClick = { }) { Text("navigate") }
    }
}