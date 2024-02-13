package com.aimicor.navcompose.android

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

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.aimicor.navcompose.common.NavComposable
import com.aimicor.navcompose.common.NavComposableSpec
import com.aimicor.navcompose.common.NavComposeParamValue
import com.aimicor.navcompose.common.toRoute

/**
 * Navigates to another composable screen.
 * @param composable The key object of the screen or composable to navigate too.
 * @param params The list of argument key value pairs to be passed to the destination composable screen.
 * @param navOptions special options for this navigation operation
 * @param navigatorExtras extras to pass to the [Navigator]
 */
fun NavController.navigate(
    composable: NavComposable,
    vararg params: NavComposeParamValue<*>,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) = when (composable) {
    is NavComposableSpec -> {
        composable.params.assertMatching(params)
        params.asList().toRoute(composable.path) { "${key}=${encode(value)}" }
    }

    else -> {
        if (params.isNotEmpty()) throw UnexpectedParamsException()
        composable.route
    }
}.also { navigate(it, navOptions, navigatorExtras) }
