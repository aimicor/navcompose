package com.aimicor.navcompose.multimoduleflatstructure

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

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.aimicor.navcompose.android.composable
import com.aimicor.navcompose.android.navComposableGroup
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navParamSpec

internal val homeComposableSpec = navComposableSpec()
internal val detailParam = navParamSpec<ComplexStuff>()
internal val detailScreen = navComposableSpec(detailParam)

val homeGroup = navComposableGroup(homeComposableSpec) {
    composable(homeComposableSpec) { HomeScreen() }
    composable(
        detailScreen,
        exitTransition = { ExitTransition.None },
        popExitTransition = { slideOutHorizontally() },
        enterTransition = { slideInHorizontally() }
    ) { DetailScreen() }
}