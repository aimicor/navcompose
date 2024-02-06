package com.aimicor.navcompose.singlemodule

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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    navParam: ComplexStuff,
    // DetailViewModel picks up param1 and param3.
    // Can use Hilt or Koin alternative here.
    viewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    Column(modifier = Modifier
        .background(color = Color.Green)
        .fillMaxSize()
    ) {
        Text(text = "Detail screen")
        Text(text = "SavedStateHandle  = ${viewModel.receivedParam1.name}")
        Text(text = "BackStackEntry  = ${navParam.name}")
        Text(text = "Default of Null = ${viewModel.receivedParam2Name}")
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(ComplexStuff("example"))
}