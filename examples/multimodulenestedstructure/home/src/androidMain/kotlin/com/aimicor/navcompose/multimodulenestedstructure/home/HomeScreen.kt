package com.aimicor.navcompose.multimodulenestedstructure.home

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

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.aimicor.navcompose.android.LocalNavController
import com.aimicor.navcompose.android.navigate
import com.aimicor.navcompose.multimodulenestedstructure.detail.ComplexStuff
import com.aimicor.navcompose.multimodulenestedstructure.detail.detailParam

@Composable
fun HomeScreen() {
    Column {
        Text(text = "home screen")
        val navController = LocalNavController.current
        Button(
            onClick = {

                /**
                 * Navigate to DetailScreen, sending non-defaulted argument
                 */
                navController.navigate(
                    detailScreen,
                    detailParam with ComplexStuff("first")
                )


            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
        ) {
            Text(
                text = "Go to detail",
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}